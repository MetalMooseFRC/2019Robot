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

public class ManualElevator extends Command {
  public ManualElevator() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myElevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // speed of elevator ascending/descending depends on the joystick's y value.
    double YSpeed = -OI.operatorStick.getRawAxis(1);

    //check deadband
    if (Math.abs(YSpeed) < Constants.elevatorStickMinimumInput) YSpeed = 0;
    
    Robot.myElevator.throttleSpeed(YSpeed, Math.signum(YSpeed));

    //System.out.println(Robot.myElevator.getEncoderCount());

    // speed of elevator x axis depending on joydtick's x value
    double XSpeed = OI.operatorStick.getRawAxis(0);
    if (Math.abs(XSpeed) < Constants.elevatorStickMinimumInput) XSpeed = 0;

    //Don't make motor go past limit
    if ( Constants.elevatorXMargin + Robot.myElevator.getEncoderXCount() > Constants.elevatorXLimit && XSpeed >0 ) XSpeed = 0;
    if ( Robot.myElevator.getEncoderXCount() - Constants.elevatorXMargin < -Constants.elevatorXLimit && XSpeed <0 ) XSpeed = 0;
    Robot.myElevator.setXSpeed(XSpeed);

    System.out.println(Robot.myElevator.getEncoderXCount());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
