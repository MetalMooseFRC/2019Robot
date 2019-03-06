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


	public ElevatorToHeight(double height) {
		// Use requires() here to declare subsystem dependencies
        requires(Robot.myElevator);

		//in encoder revolutions
        this.height = height;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
         //Set reference for elevator PID
		// Robot.myElevator.elevatorPID.setSetpoint(height);
		// Robot.myElevator.elevatorPID.enable();
		System.out.println("start");
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//Appoach desired height
		Robot.myElevator.setHeight(height);

		System.out.println(Robot.myElevator.getEncoderCount());

		//Continue to approach references
		//double speed = Robot.myElevator.elevatorPID.get();

		//Robot.myElevator.elevatorMotor.set(ControlMode.PercentOutput, speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//Finish once within a margin of error of the setpoint
		//return Robot.myElevator.elevatorPID.onTarget(); 
		return Math.abs(height - Robot.myElevator.getEncoderCount()) < Constants.PIDElevatorErrorMargin;
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
