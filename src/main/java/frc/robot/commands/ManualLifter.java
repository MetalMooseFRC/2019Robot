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

public class ManualLifter extends Command {


  public ManualLifter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myLifter);


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     
     //bring both down at the same time
     if (OI.lifterOutButton.get()) {
      Robot.myLifter.setLeftSpeed(0.5);
      Robot.myLifter.setRightSpeed(0.5);
    }

     //lift both up at the same time but compensate if the encoders go out of sync between sides
      else if (OI.lifterInButton.get()) {
        //left lifter
        if (Robot.myLifter.getLeftEncoder() > 15 && Robot.myLifter.getLeftEncoder() < 160) {
       Robot.myLifter.setLeftSpeed(-0.5);
      }
       else if (Robot.myLifter.getLeftEncoder() > 5) {
        Robot.myLifter.setLeftSpeed(-0.25);
       } 
       //right lifter
       if (Robot.myLifter.getRightEncoder() < -15 && Robot.myLifter.getRightEncoder() > -160) {
        Robot.myLifter.setRightSpeed(-0.5);
       }
        else if (Robot.myLifter.getRightEncoder() < -5) {
         Robot.myLifter.setRightSpeed(-0.25);
        } 
     } else {
      Robot.myLifter.setLeftSpeed(0);
      Robot.myLifter.setRightSpeed(0);
     }

    //System.out.println("L " + Robot.myLifter.getLeftEncoder() + " R " + Robot.myLifter.getRightEncoder());
     
    
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
