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

    if (OI.armInButton.get()) {
      armSpeed = -0.6;
    } else if (OI.armOutButton.get()) {
      armSpeed = 0.6;
    } else {
      armSpeed = 0;
    }
    
    //Auxilary controller
    if (Constants.operatorBoardMode == 1) {

    if (!OI.armInButton.get() && !OI.armOutButton.get()) armSpeed = -OI.operatorController.getRawAxis(RobotMap.armAxisAuxPort)/2;

    //Button pad
  } else {
    if (!OI.armInButton.get() && !OI.armOutButton.get()) armSpeed = OI.operatorRightPad.getRawAxis(RobotMap.armAxisPort)/2;
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
