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


	public DrivetrainDriveDistance(double distance) {
		// Use requires() here to declare subsystem dependencies
        //requires(Robot.myDrivetrain);

        this.distance = distance;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//zero drivetrain encoders
		Robot.myDrivetrain.resetEncoder();

		//Set references
		Robot.myDrivetrain.setRightPosition(distance);
		
		Robot.myDrivetrain.myAHRS.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {


		//Continue to approach references
	   Robot.myDrivetrain.setRightPosition(distance);
	   double rightMotorSpeed = Robot.myDrivetrain.rightFrontDriveMotor.getAppliedOutput();

	   //have the left mirror the right motor speed (not accurate but Spark PID is weird)
	   Robot.myDrivetrain.leftFrontDriveMotor.set(-rightMotorSpeed);

		//System.out.println("D E " + Robot.myDrivetrain.getRightEncoderCount());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//Finish once within a margin of error of the setpoint
        return Math.abs(distance - Robot.myDrivetrain.getRightEncoderCount()) < Constants.PIDDriveErrorMargin;
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
