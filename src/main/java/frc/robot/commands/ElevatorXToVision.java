/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constants;



public class ElevatorXToVision extends Command {

int visionTargetXCoord;
int target = 0;

double error;
double integral;
double output;


  public ElevatorXToVision() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myElevator);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    integral = 0;
    //set motor speed to 0
   Robot.myElevator.elevatorXMotor.set(ControlMode.PercentOutput, 0);
 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //convert serial string to int
    visionTargetXCoord = Integer.parseInt(Robot.jevoisSerial.readString());
    System.out.println(visionTargetXCoord);

    error = target - visionTargetXCoord;
    integral += error;
    output = Constants.visionP*error + Constants.visionI*integral;

    Robot.myElevator.elevatorXMotor.set(ControlMode.PercentOutput, output);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //finish when close enough to target
    return Math.abs(visionTargetXCoord - target) < Constants.visionMargin;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("On Target");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
