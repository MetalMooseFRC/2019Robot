/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constants;



public class ElevatorXToPosition extends Command {

  //These are measured in encoder tics
  private double pos;

  public ElevatorXToPosition(double pos) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myElevator);

    this.pos = pos;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  //Set PID values
  Robot.myElevator.elevatorXMotor.config_kP(0, Constants.elevatorXP);
  Robot.myElevator.elevatorXMotor.config_kI(0, Constants.elevatorXI);
  Robot.myElevator.elevatorXMotor.config_kD(0, Constants.elevatorXD);

  //Turn off motor
   Robot.myElevator.setXSpeed(0);
   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.myElevator.elevatorXMotor.set(ControlMode.Position, pos);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //finish when close enough to target
    return Math.abs(Robot.myElevator.getEncoderXCount() - pos) < Constants.elevatorXMargin;
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
