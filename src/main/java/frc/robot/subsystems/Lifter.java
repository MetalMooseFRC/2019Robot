/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.BlankPIDOutput;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ManualCollector;
import frc.robot.commands.ManualLifter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Lifter extends Subsystem {

    private CANSparkMax leftLifterMotor = new CANSparkMax(Constants.leftLifterMotorCANID, MotorType.kBrushless);
    private CANSparkMax rightLifterMotor = new CANSparkMax(Constants.rightLifterMotorCANID, MotorType.kBrushless);

    //private TalonSRX leftLifterMotor = new TalonSRX(3);
    //private TalonSRX rightLifterMotor = new TalonSRX(4);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualLifter());
  }

  public Lifter() {

    rightLifterMotor.follow(leftLifterMotor, true);

   /**  rightLifterMotor.setInverted(true);
    rightLifterMotor.follow(leftLifterMotor);

    //Configure motor controllers
    rightLifterMotor.configFactoryDefault();
    rightLifterMotor.configPeakOutputForward(1);
    rightLifterMotor.configPeakOutputReverse(-1);
    leftLifterMotor.configFactoryDefault();
    leftLifterMotor.configPeakOutputForward(1);
    leftLifterMotor.configPeakOutputReverse(-1);
    */
  }

  public void setSpeed(double speed) {
      //leftLifterMotor.set(ControlMode.PercentOutput, speed);
      leftLifterMotor.set(speed);
  }


  
}
