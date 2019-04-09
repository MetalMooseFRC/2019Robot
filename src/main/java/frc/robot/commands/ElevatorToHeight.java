/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;



/**
 * An example command.  You can replace me with your own command.
 */
public class ElevatorToHeight extends Command {

	double height;
	int backUpCount;
	boolean backUp = false;


	public ElevatorToHeight(double height) {
		// Use requires() here to declare subsystem dependencies
        requires(Robot.myElevator);

		//in encoder revolutions
		this.height = height;
		backUp = false;
	}

	//back up constructor for if the elevator is caught on something and can't go up
	public ElevatorToHeight(double height, boolean backUpIfTImeOut) {
		// Use requires() here to declare subsystem dependencies
        requires(Robot.myElevator);

		//in encoder revolutions
		this.height = height;
		backUp = backUpIfTImeOut;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		System.out.println("start elevator");
		
		setTimeout(0.45);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

			//Appoach desired height
			Robot.myElevator.setHeight(height);

			//start backing up if it appears stuck
			if (isTimedOut() && backUp) {
				Robot.myDrivetrain.arcadeDrive(0.05, 0);
			}

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

			//Finish once within a margin of error of the setpoint
		if (backUp) {return Math.abs(height - Robot.myElevator.getEncoderCount()) < Constants.PIDElevatorErrorMargin + 0.5;}
		else {return Math.abs(height - Robot.myElevator.getEncoderCount()) < Constants.PIDElevatorErrorMargin;}

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	   Constants.isLinedUp = false;
	   Constants.isRocketHatch = false;
       System.out.println("ended elevator");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Constants.isLinedUp = false;
		Constants.isRocketHatch = false;

	}
}
