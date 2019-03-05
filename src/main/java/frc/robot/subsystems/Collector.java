/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.BlankPIDOutput;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ManualCollector;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Collector extends Subsystem {

    private TalonSRX collectorMotor = new TalonSRX(RobotMap.collectorMotorCANID);
  
    public boolean isHoldingBall;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualCollector());
  }

  public Collector() {

    isHoldingBall = true;
  }

  public void intake() {
   setThrottledSpeed(Constants.intakeSpeed);
  }

  public void outtake() {
    setSpeed(Constants.outtakeSpeed);
  }

  //speed to hold ball, small intake force
  public void hold() {
    setSpeed(Constants.holdSpeed);
  }

  public void setSpeed(double speed) {
    collectorMotor.set(ControlMode.PercentOutput, speed);
  }

  //Make it intake faster if driving faster
  public void setThrottledSpeed(double speed) {
    double averageDriveSpeed = (Robot.myDrivetrain.leftFrontDriveMotor.getAppliedOutput() + Robot.myDrivetrain.rightFrontDriveMotor.getAppliedOutput())/2;

    if (averageDriveSpeed > Constants.intakeSpeed) {
      speed =+ 0.5*averageDriveSpeed;
    }

    setSpeed(speed);
  }
  
}
