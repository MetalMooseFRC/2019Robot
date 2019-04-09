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

public class SetNotRocketHatch extends Command {

  private boolean isDone;

  public SetNotRocketHatch() {

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      isDone = false;
      //start searching for normal angles
      Constants.isRocketHatch = false;
      
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      isDone = true;
      
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone;
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
