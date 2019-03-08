/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class Climb extends Command {
  //Rotations for lifters
  double pos;

  public Climb(double pos) {
      requires(Robot.myLifter);

      this.pos = pos;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

      
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.myLifter.setLeftSpeed(1);
      Robot.myLifter.setRightSpeed(1);
      Robot.myElevator.setSpeed(-0.085);

      
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.myLifter.getLeftEncoder() > pos;
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
