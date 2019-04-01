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
public class DrivetrainDriveTime extends Command {

    double time;
    double speed;


	public DrivetrainDriveTime(double time, double speed) {
		// Use requires() here to declare subsystem dependencies
        //requires(Robot.myDrivetrain);

        this.time = time;
	this.speed = speed;
	}

	public DrivetrainDriveTime(double time, double speed, int areLegsUp) {
		// Use requires() here to declare subsystem dependencies
        //requires(Robot.myDrivetrain);

        this.time = time;
	this.speed = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	//if (Constants.areLegsUp == 1) time += 5;
        setTimeout(time);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	if (Constants.areLegsUp == 0 || Constants.areLegsUp == 2) Robot.myDrivetrain.throttledArcade(speed, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
        return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Constants.isLinedUp = false;
       		System.out.println("ended");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
