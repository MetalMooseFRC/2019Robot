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

		Robot.myDrivetrain.arcadeDrive(speed, turn);


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
