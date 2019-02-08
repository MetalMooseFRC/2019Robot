/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.BlankPIDOutput;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ManualElevator;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  //create motor controller for elevator
   public CANSparkMax elevatorRightMotor = new CANSparkMax(RobotMap.elevatorRightMotorCANID, MotorType.kBrushless);
   public CANSparkMax elevatorLeftMotor = new CANSparkMax(RobotMap.elevatorLeftMotorCANID, MotorType.kBrushless);
   public TalonSRX elevatorXMotor = new TalonSRX(RobotMap.elevatorXMotorCANID);

  //create sensors for elevator
  public CANEncoder elevatorEncoder = new CANEncoder(elevatorRightMotor);
  public AnalogPotentiometer XPot = new AnalogPotentiometer(RobotMap.potAnalogPin);

  //PID controller
  public CANPIDController elevatorPID = new CANPIDController(elevatorRightMotor);
  public PIDController elevatorXPID = new PIDController(Constants.elevatorXP, Constants.elevatorXI, Constants.elevatorXD, XPot, new BlankPIDOutput());

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualElevator());
  }

  public Elevator() {
    elevatorLeftMotor.follow(elevatorRightMotor, true);

    elevatorXMotor.configFactoryDefault();
    elevatorXMotor.setNeutralMode(NeutralMode.Brake);

    elevatorXPID.setOutputRange(-0.5, 0.5);
    elevatorXPID.setAbsoluteTolerance(Constants.elevatorXMargin);

  }

  //set elevator speed at a constant
  public void setSpeed(double speed) {
    elevatorRightMotor.set(speed);
  }

  //in percent output mode
  public void setXSpeed(double speed) {
    elevatorXMotor.set(ControlMode.PercentOutput, speed);
  }

  public double getEncoderCount() {
    return elevatorEncoder.getPosition();
  }

  public double getXPotCount() {
    return XPot.get();
  }


  //set PID reference point
  public void setHeight(double pos) {
    elevatorPID.setP(Constants.elevatorP);
    elevatorPID.setI(Constants.elevatorI);
    elevatorPID.setD(Constants.elevatorD);
    elevatorPID.setOutputRange(-1, 1);
    elevatorPID.setReference(pos, ControlType.kPosition);
  } 
}
