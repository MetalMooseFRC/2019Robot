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
  public CANSparkMax leftElevatorMotor = new CANSparkMax(RobotMap.elevatorLeftMotorCANID, MotorType.kBrushless);
  public CANSparkMax rightElevatorMotor = new CANSparkMax(RobotMap.elevatorRightMotorCANID, MotorType.kBrushless);

    // public TalonSRX elevatorMotor = new TalonSRX(RobotMap.elevatorMotorCANID);

  //create sensors for elevator
//private Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderAPort, RobotMap.elevatorEncoderBPort);
private CANEncoder elevatorEncoder = new CANEncoder(leftElevatorMotor);

  //PID controller
//public PIDController elevatorPID = new PIDController(Constants.elevatorP, Constants.elevatorI, Constants.elevatorD, elevatorEncoder, new BlankPIDOutput());
private CANPIDController elevatorPID = new CANPIDController(leftElevatorMotor);

// Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualElevator());
  }

  public Elevator() {
    rightElevatorMotor.follow(leftElevatorMotor, true);

    elevatorPID.setOutputRange(-1, 1);
    elevatorEncoder.setPosition(0);

    //set PID values
    elevatorPID.setP(Constants.elevatorP);
    elevatorPID.setI(Constants.elevatorI);
    elevatorPID.setD(Constants.elevatorD);


/** 
    elevatorMotor.configFactoryDefault();
    elevatorMotor.setNeutralMode(NeutralMode.Brake);
    elevatorMotor.configPeakOutputForward(1.0);
    elevatorMotor.configPeakOutputReverse(-1.0);

    elevatorPID.setAbsoluteTolerance(Constants.PIDElevatorErrorMargin);
    elevatorPID.setOutputRange(-1, 1);
    */

  }

  //set elevator speed at a constant
  public void setSpeed(double speed) {
    //elevatorMotor.set(ControlMode.PercentOutput, speed);
    leftElevatorMotor.set(speed);

  }

  //speed needed to hold elevator against gravity
  public void hold() {
   setSpeed(Constants.elevatorHoldSpeed);
  }

  //throttle speed to prevent elevator slamming
  public void  throttleSpeed(double speed, double direction) {
    double pos = getEncoderCount();
    if (pos < 0) pos = 0;
    if (pos > Constants.elevatorLimit) pos = Constants.elevatorLimit;

    if (direction > 0) {
      if (pos > 3*Constants.elevatorLimit/4) {
        //proportionally slow the motor
        setSpeed(speed*(Constants.elevatorLimit-pos)/Constants.elevatorLimit + Constants.elevatorHoldSpeed);
      } else {
        setSpeed(speed);
      }

    } else if (direction < 0) {
      //proportionally slow the motor
      if (pos < Constants.elevatorLimit/2) {
        //Add one to move the "zero"/ bottom of the elevator
        setSpeed(speed*(pos+3)/Constants.elevatorLimit + Constants.elevatorHoldSpeed);
      } else {
        setSpeed(speed);
      }

    //only hold if higher up, not needed when at the ground
    } else if (pos > 2) {
      hold();
    } 
  }

  //in percent output mode
  public void setXSpeed(double speed) {
    //elevatorXMotor.set(ControlMode.PercentOutput, speed);
  }

  public double getEncoderCount() {
    return elevatorEncoder.getPosition();
  }


  //public double getEncoderXCount() {
    //return elevatorXMotor.getSelectedSensorPosition();
  //}

  //PID reference point
  public void setHeight(double height) {
    elevatorPID.setReference(height, ControlType.kPosition);
  }

}
