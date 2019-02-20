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

  public CollectorArmToPosition(double pos) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myCollector);

    this.pos = pos;


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      //Set PID values
    Robot.myCollector.armMotor.config_kP(1, Constants.armP);
    Robot.myCollector.armMotor.config_kI(1, Constants.armI);
    Robot.myCollector.armMotor.config_kD(1, Constants.armD);


    //Turn off motor
    Robot.myCollector.setArmSpeed(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.myCollector.armMotor.set(ControlMode.Position, pos);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(pos - Robot.myCollector.getEncoderCount()) < Constants.armMargin;
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
