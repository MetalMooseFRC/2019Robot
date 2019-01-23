/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  //create motor controller for elevator
   public CANSparkMax elevatorMotor = new CANSparkMax(RobotMap.elevatorMotorCANID, MotorType.kBrushless);

  //create encoder for elevator
  public CANEncoder elevatorEncoder = new CANEncoder(elevatorMotor);

  //PID controller
  private CANPIDController elevatorPID = new CANPIDController(elevatorMotor);
  

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

   
  //set elevator speed at a constant
  public void setSpeed(double speed) {
    elevatorMotor.set(speed);
  }

  public double getEncoderCount() {
    return elevatorEncoder.getPosition();
  }

  //set PID reference point
  public void setPosition(double pos) {
    elevatorPID.setP(Constants.elevatorP);
    elevatorPID.setI(Constants.elevatorI);
    elevatorPID.setD(Constants.elevatorD);
    elevatorPID.setOutputRange(-1, 1);
    elevatorPID.setReference(pos, ControlType.kPosition);
  } 
}
