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

public class CollectorArmToPosition extends Command {
    //in encoder tics
    private double pos;
    private double speed;

  public CollectorArmToPosition(double pos, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myArm);

    this.pos = pos;
    this.speed = speed;


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    //Turn off motor
    Robot.myArm.setArmSpeed(0);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //speed is a priority, not accuracy
    Robot.myArm.setArmSpeed(speed);

    //System.out.println(Robot.myArm.getEncoderCount());
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //based on direction, determine if in a certain margin of the setpoint
    if (Math.signum(speed) > 0) {
      return Robot.myArm.getEncoderCount() < pos + Constants.armMargin;
    } else {
      return Robot.myArm.getEncoderCount() > pos - Constants.armMargin;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.myArm.setArmSpeed(0);
    System.out.println("finished");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.myArm.setArmSpeed(0);
    Constants.isRocketHatch = false;
  }
}
