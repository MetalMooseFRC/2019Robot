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

public class ManualArm extends Command {


  public ManualArm() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myArm);


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double armSpeed;

    
    //Auxilary controller if no driver input
    if (Constants.operatorBoardMode == 1) {

    armSpeed = -OI.operatorController.getRawAxis(RobotMap.armAxisAuxPort)*0.6;

    //Button pad if no driver input
  } else {
    armSpeed = OI.operatorRightPad.getRawAxis(RobotMap.armAxisPort)*0.6;
  }


  if (Math.abs(armSpeed) < 0.1 && Robot.myArm.getEncoderCount() < -30 && Robot.myArm.getEncoderCount() > -700) {

    armSpeed = -0.5;
  }

  Robot.myArm.setArmSpeed(armSpeed);
    
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
