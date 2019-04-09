/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class DrivetrainManualDrive extends Command {

	private boolean isShiftingRight;
	private boolean isShifting = false;
	private int leftShiftCount = 0;
	private int rightShiftCount = 0;
	private double moveDirection;

	private double angles[] = {-90, 0, 90, 180, -180};
	private double rocketAngles[] = {-150, -30, 30, 150};

	public DrivetrainManualDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.myDrivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Robot.myDrivetrain.gyroPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		//Get the forward and backwards value of joystick
		double speed = OI.driverStick.getY();
		if (Math.abs(speed) < Constants.driveStickMinimumInput) {speed = 0;}
		else if (speed < 0) {speed += Constants.driveStickMinimumInput;}
		else if (speed > 0) {speed -= Constants.driveStickMinimumInput;}

		//Get the twist value of joystick, inverted since left is negative
		double turn = OI.driverStick.getRawAxis(RobotMap.driveStickZAxisPort)/2;
		if (Math.abs(turn) < Constants.driveStickMinimumInput) {turn = 0;}
		else if (turn < 0) {turn += Constants.driveStickMinimumInput;}
		else if (turn > 0) {turn -= Constants.driveStickMinimumInput;}

		//auto angle presets
		double navAngle = Robot.myDrivetrain.myAHRS.getYaw();

		double angle = navAngle;
		
		//find nearest angle
		if (Constants.isRocketHatch) {
			for (double presets : rocketAngles) {
				if (Math.abs(presets - navAngle) < 30) {
				  angle = presets;
				}
		  }

		//find nearest angle
		} else {
			for (double presets : angles) {
		  		if (Math.abs(presets - navAngle) <= 40) {
					angle = presets;
		  		}
			}
		}


		Robot.myDrivetrain.gyroPID.setSetpoint(angle);
		double auxTurn = Robot.myDrivetrain.gyroPID.get();

		//approach mode
		if (OI.approachButton.get()) {
			Constants.isApproachMode = true;

			double shiftDirection = OI.driverStick.getX();
			//shift deadband
			if (Math.abs(shiftDirection) < 0.4) {shiftDirection = 0;}

			if (!isShifting) {
				//when it is not shifting, wait until the joystick is jerked in a direction
				moveDirection = Math.signum(speed);
				if (moveDirection == 0) moveDirection = -1;

				if (shiftDirection > 0) {
					isShiftingRight = true;
					leftShiftCount = 10;
					rightShiftCount = 10;
					isShifting = true;

				} else if (shiftDirection < 0) {
					isShiftingRight = false;
					rightShiftCount = 10;
					leftShiftCount = 10;
					isShifting = true;
				} else {
					//move straight at a constant speed forwards or backwards
					if (speed < -0.2) {Robot.myDrivetrain.arcadeDrive(-0.25, auxTurn);}
					else if (speed > 0.2) {Robot.myDrivetrain.arcadeDrive(0.03, auxTurn);}
					else {Robot.myDrivetrain.arcadeDrive(-0.1, auxTurn);}
				}
			} else {
				//when it is shifting, decrease the shift counts one side at a time
				if (leftShiftCount > 0 && !isShiftingRight) {
					leftShiftCount--;
					System.out.println("GO RIGHT MOTOR");
					Robot.myDrivetrain.setRightSpeed(moveDirection*0.3);
					Robot.myDrivetrain.setLeftSpeed(0);

				} else if (leftShiftCount <= 0 && !isShiftingRight){
					isShiftingRight = true;
				} else if (rightShiftCount > 0 && isShiftingRight) {
					rightShiftCount--;
					System.out.println("GO LEFT MOTOR");
					Robot.myDrivetrain.setLeftSpeed(moveDirection*0.3);
					Robot.myDrivetrain.setRightSpeed(0); 

				} else if (rightShiftCount <= 0 && isShiftingRight){
					isShiftingRight = false;
				} 
			}
			//end shift
			if (rightShiftCount == 0 && leftShiftCount == 0) {
				isShifting = false;
			}
				
		} else {
			//reset approach mode after button press
			Constants.isApproachMode = false;
			rightShiftCount = 0;
			leftShiftCount = 0;
			isShifting = false;

			//if the slower button is pressed, no need to "double throttle"
			if (OI.slowerDriveButton.get()) {
				Robot.myDrivetrain.arcadeDrive(speed/3, turn/3);
			} else {
				Robot.myDrivetrain.throttledArcade(speed, turn);
			}
		}
		

		


	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
