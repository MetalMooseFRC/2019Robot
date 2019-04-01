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

public class CollectorOuttake extends Command {
    double time;

  public CollectorOuttake(double time) {
    requires(Robot.myCollector);

    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      setTimeout(time);

}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.myCollector.outtake();
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.myCollector.outtake();;

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
