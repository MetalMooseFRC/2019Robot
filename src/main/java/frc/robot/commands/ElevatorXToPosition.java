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

  private double pos;

  public ElevatorXToPosition(double pos) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myDrivetrain);
    this.pos = pos;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //turn off motor
   Robot.myElevator.elevatorXMotor.set(ControlMode.PercentOutput, 0);

   //zero encoder with 30ms timeout
   Robot.myElevator.elevatorXMotor.getSensorCollection().setQuadraturePosition(0, 30);
   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //approach the position translated into encoder ticks
    Robot.myElevator.elevatorXMotor.set(ControlMode.Position, pos/Constants.encoderToDistanceCoefficient);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //finish when close enough to target
    return Math.abs(Robot.myElevator.elevatorXMotor.getSelectedSensorPosition()*Constants.encoderToDistanceCoefficient - pos) < Constants.elevatorXMargin;
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
