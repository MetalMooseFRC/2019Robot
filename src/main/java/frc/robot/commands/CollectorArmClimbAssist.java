/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class CollectorArmClimbAssist extends Command {
    //in encoder tics
    private double posToGo;
    private boolean isExtending;

  public CollectorArmClimbAssist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myArm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.myArm.armEncoder.reset();
    isExtending = true;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println(-Robot.myArm.getEncoderCount() + " " + isExtending);
    double currentPos = -Robot.myArm.getEncoderCount();

    //Go out when exteding at fast speed
    if (currentPos < Constants.armTwoInchInTics - Constants.armMargin && isExtending) {
        Robot.myArm.setArmSpeed(0.8);
    } else if (currentPos >= Constants.armTwoInchInTics - Constants.armMargin) {
      isExtending = false;
    } else {
      Robot.myArm.setArmSpeed(0);
    }
    
    //Retract slowly
    if (currentPos > 0 + Constants.armMargin && !isExtending) {
        Robot.myArm.setArmSpeed(-0.4);
    } else if (currentPos < 0 + Constants.armTwoInchInTics) {
      isExtending = true;
    } else {
      Robot.myArm.setArmSpeed(0);
    } 

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //finishes when the button is released
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
