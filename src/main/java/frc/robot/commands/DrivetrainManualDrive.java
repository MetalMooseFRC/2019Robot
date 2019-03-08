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
	public DrivetrainManualDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.myDrivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double leftSpeed;
		double rightSpeed;
		//Get the forward and backwards value of joystick
		//Inverted because forward is negative and backwards is positive
		double speed = OI.driverStick.getY();
		if (Math.abs(speed) < Constants.driveStickMinimumInput) {speed = 0;}
		else if (speed < 0) {speed += Constants.driveStickMinimumInput;}
		else if (speed > 0) {speed -= Constants.driveStickMinimumInput;}

		//Get the twist value of joystick, inverted since left is negative
		double turn = OI.driverStick.getRawAxis(RobotMap.driveStickZAxisPort)/2;
		if (Math.abs(turn) < Constants.driveStickMinimumInput) {turn = 0;}
		else if (turn < 0) {turn += Constants.driveStickMinimumInput;}
		else if (turn > 0) {turn -= Constants.driveStickMinimumInput;}

		//Slow if the button is pressed else only slow when the elevator is up
		if (OI.slowerDriveButton.get()) {
			speed = speed/3;
			turn = turn/3;
			Robot.myDrivetrain.arcadeDrive(speed, turn);
		
		//normal elevator throttled drive
		} else {
			/**
			if (Constants.isSearchingForLine) {
				//If it sees a white line, go forward and will auto calibrate to line
				if (Robot.myDrivetrain.getLeftInReflectance()){
					leftSpeed = Robot.myDrivetrain.correctLineFollowing(speed, Robot.myDrivetrain.getLeftOutReflectance(), true);
					rightSpeed = Robot.myDrivetrain.correctLineFollowing(speed, Robot.myDrivetrain.getLeftOutReflectance(), false);
					Robot.myDrivetrain.setLeftSpeed(leftSpeed);
					Robot.myDrivetrain.setRightSpeed(rightSpeed);
				} else if (Robot.myDrivetrain.getRightInReflectance()){
					leftSpeed = Robot.myDrivetrain.correctLineFollowing(speed, Robot.myDrivetrain.getRightOutReflectance(), false);
					rightSpeed = Robot.myDrivetrain.correctLineFollowing(speed, Robot.myDrivetrain.getRightOutReflectance(), true);
					Robot.myDrivetrain.setLeftSpeed(leftSpeed);
					Robot.myDrivetrain.setRightSpeed(rightSpeed); 
				} else {
					Robot.myDrivetrain.throttledArcade(speed, turn);
				}
			} else {
			Robot.myDrivetrain.throttledArcade(speed, turn);
			} */

			System.out.println(" LO " + Robot.myDrivetrain.reflectanceLeftOutsideSensor.getVoltage() + " LI " + Robot.myDrivetrain.reflectanceLeftInsideSensor.getVoltage() + " RI " + Robot.myDrivetrain.reflectanceRightInsideSensor.getVoltage() + " RO " + Robot.myDrivetrain.reflectanceRightOutsideSensor.getVoltage());
			Robot.myDrivetrain.throttledArcade(speed, turn);

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
