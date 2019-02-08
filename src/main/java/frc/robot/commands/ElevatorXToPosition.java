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
  private double posToGo;
  private double dist;
  private double currentPos;

  public ElevatorXToPosition(double dist) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myElevator);

    this.dist = dist;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    currentPos = Robot.myElevator.getXPotCount();
    
    //Make position relative because we want 0 to stay in the center
    posToGo = currentPos + dist;

    //turn off motor
   Robot.myElevator.setXSpeed(0);

   Robot.myElevator.elevatorXPID.setSetpoint(posToGo);
   Robot.myElevator.elevatorXPID.reset();
   Robot.myElevator.elevatorXPID.enable();
 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Get PID output from analog potentiometer
    double speed = Robot.myElevator.elevatorXPID.get();
    Robot.myElevator.setXSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //finish when close enough to target
    return Robot.myElevator.elevatorXPID.onTarget();
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
