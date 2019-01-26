/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DrivetrainDriveAngle extends Command {

  private double angle;

  public DrivetrainDriveAngle(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.myDrivetrain);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.myDrivetrain.myAHRS.reset();

    // Set point, enable gyro PID
    Robot.myDrivetrain.gyroPID.setSetpoint(angle);
    Robot.myDrivetrain.gyroPID.reset();
    Robot.myDrivetrain.gyroPID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  //keep adjusting the motors, depending on the output of PID.
  @Override
  protected void execute() {
    double xSpeed = Robot.myDrivetrain.gyroPID.get();
    System.out.println("PID Speed" + xSpeed);
    System.out.println("Gyro" + Robot.myDrivetrain.myAHRS.getAngle());
    Robot.myDrivetrain.arcadeDrive(0, xSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  //check if gyro is on target or not.
  @Override
  protected boolean isFinished() {
    return Robot.myDrivetrain.gyroPID.onTarget();
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
