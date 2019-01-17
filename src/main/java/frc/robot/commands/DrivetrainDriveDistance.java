/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;



/**
 * An example command.  You can replace me with your own command.
 */
public class DrivetrainDriveDistance extends Command {

    double distance;
    double encoderRightSetpoint;
    double encoderLeftSetpoint;


	public DrivetrainDriveDistance(double distance) {
		// Use requires() here to declare subsystem dependencies
        requires(Robot.myDrivetrain);

        this.distance = distance;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
         //no encoder reset so relative positioning must be used
         double currentRightCount = Robot.myDrivetrain.getRightEncoderCount();
         encoderRightSetpoint = currentRightCount + distance;

         double currentLeftCount = Robot.myDrivetrain.getLeftEncoderCount();
         encoderLeftSetpoint = currentLeftCount + distance;


         Robot.myDrivetrain.setRightPosition(encoderRightSetpoint);
         //Robot.myDrivetrain.setLeftPosition(encoderLeftSetpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        Robot.myDrivetrain.setRightPosition(encoderRightSetpoint);
        //Robot.myDrivetrain.setLeftPosition(encoderLeftSetpoint);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
        return Math.abs(encoderRightSetpoint - Robot.myDrivetrain.getRightEncoderCount()) < Constants.PIDErrorMargin; 
        //&& Math.abs(encoderLeftSetpoint - Robot.myDrivetrain.getLeftEncoderCount()) < Constants.PIDErrorMargin;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
       System.out.println("ended");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
