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
    public TalonSRX armMotor = new TalonSRX(RobotMap.collectorArmMotorCANID);

    private Encoder armEncoder = new Encoder(RobotMap.encoderAPort, RobotMap.encoderBPort);
    public PIDController armPID = new PIDController(Constants.armP, Constants.armI, Constants.armD, armEncoder, new BlankPIDOutput());

    public boolean isHoldingBall;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualCollector());
  }

  public Collector() {
    armMotor.configFactoryDefault();
    //armMotor.selectProfileSlot(1, 0);
    armMotor.setNeutralMode(NeutralMode.Brake);
    //armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    armMotor.configPeakOutputForward(1.0);
    armMotor.configPeakOutputReverse(-1.0);

    armPID.setOutputRange(-1, 1);
    armPID.setAbsoluteTolerance(Constants.armMargin);
    armPID.reset();

    isHoldingBall = true;
  }

  public void intake() {
    collectorMotor.set(ControlMode.PercentOutput, Constants.intakeSpeed);
  }

  public void outtake() {
    collectorMotor.set(ControlMode.PercentOutput, Constants.outtakeSpeed);
  }

  //speed to hold ball, small intake force
  public void hold() {
    collectorMotor.set(ControlMode.PercentOutput, Constants.holdSpeed);
  }

  public double getEncoderCount() {
    return armMotor.getSelectedSensorPosition();
  }

  public void setArmSpeed(double speed) {
    armMotor.set(ControlMode.PercentOutput, speed);
  }

  
}
