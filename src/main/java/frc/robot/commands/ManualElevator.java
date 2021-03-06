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

    //Depends on operator mode
    double YSpeed = 0;
    double SlowSpeed = 0;
    if (Constants.operatorBoardMode == 1) {
      YSpeed = -OI.operatorController.getRawAxis(RobotMap.elevatorAxisPort);
    } else {
      YSpeed = -OI.operatorLeftPad.getRawAxis(RobotMap.elevatorAxisPort)/2;
      SlowSpeed = OI.operatorLeftPad.getRawAxis(RobotMap.elevatorXAxisPort)/5;

    }

    //check deadband
    if (Math.abs(YSpeed) < Constants.elevatorStickMinimumInput) {
      YSpeed = 0; 
    }
    
    //if joystick is going to the side, raise the elevator at a constant slower speed
    if (SlowSpeed > 0) {
      Robot.myElevator.setSpeed(SlowSpeed);
    } else {
      Robot.myElevator.throttleSpeed(YSpeed, Math.signum(YSpeed));
    }


   //System.out.println("E " + Robot.myElevator.getEncoderCount());

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
