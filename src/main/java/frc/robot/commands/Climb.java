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
  int HABLevel;

  public Climb(double pos, int HABLevel) {
      requires(Robot.myLifter);

      this.pos = pos;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Constants.areLegsUp = 1;
      
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Activate both lifters and the elevator
      Robot.myLifter.setLeftSpeed(1);
      Robot.myLifter.setRightSpeed(1);
      Robot.myElevator.setSpeed(-0.155);

      if (Robot.myLifter.getLeftEncoder() > 30* HABLevel) Constants.areLegsUp = 2;


  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.myLifter.getLeftEncoder() > pos || Robot.myLifter.getRightEncoder() > pos;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Constants.areLegsUp = 0;
    Robot.myLifter.setLeftSpeed(0);
    Robot.myLifter.setRightSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
