/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainManualDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	
	//Motor controllers
	private CANSparkMax rightFrontDriveMotor = new CANSparkMax(RobotMap.rightFrontDriveCANID, MotorType.kBrushless);
	//private CANSparkMax rightMiddleDriveMotor = new CANSparkMax(RobotMap.rightMiddleDriveCANID, MotorType.kBrushless);
	//private CANSparkMax rightBackDriveMotor = new CANSparkMax(RobotMap.rightBackDriveCANID, MotorType.kBrushless);

	private CANSparkMax leftFrontDriveMotor = new CANSparkMax(RobotMap.leftFrontDriveCANID, MotorType.kBrushless);
	//private CANSparkMax leftMiddleDriveMotor = new CANSparkMax(RobotMap.leftMiddleDriveCANID, MotorType.kBrushless);
	//private CANSparkMax leftBackDriveMotor = new CANSparkMax(RobotMap.leftBackDriveCANID, MotorType.kBrushless);

	private CANEncoder rightSparkMaxEncoder = new CANEncoder(rightFrontDriveMotor);
	private CANEncoder leftSparkMaxEncoder = new CANEncoder(leftFrontDriveMotor);
	CANPIDController rightPID = new CANPIDController(rightFrontDriveMotor);
	CANPIDController leftPID = new CANPIDController(leftFrontDriveMotor);
	
	public void initDefaultCommand() {
		// Set the default command to manual driving
		setDefaultCommand(new DrivetrainManualDrive());
	}

	//speed is forward or backwards speed
	//turn is the turning speed
			//We cannot have differential drive and autonomous SparkMax motors
	public void arcadeDrive(double speed, double turn) {
		//Arcade drive algorithem
		double leftPower = speed - turn;
		double rightPower = speed + turn;

		//Adjust other motor to compensate if speed is > 1
		leftPower += skim(rightPower);
		rightPower += skim(leftPower);

		leftFrontDriveMotor.set(leftPower);
		rightFrontDriveMotor.set(-rightPower);

	}

	public double getRightEncoderCount() {
		return rightSparkMaxEncoder.getPosition();
	}

	public double getLeftEncoderCount() {
		return leftSparkMaxEncoder.getPosition();
	}

	//Method to alter motors to get appropriate turn and not have speed exceed 1
	double skim(double vel) {
		if (vel > 1) {
			return -((vel-1)*Constants.turnGain);
		} else if (vel < -1) {
			return -((vel + 1)*Constants.turnGain);
		}
		return 0;
	}

	public void setRightPosition(double pos) {
		
		rightPID.setP(Constants.driveP);
		rightPID.setI(Constants.driveI);
		rightPID.setD(Constants.driveD);
		rightPID.setOutputRange(-1, 1);
		rightPID.setReference(pos, ControlType.kPosition);
	}

	public void setLeftPosition(double pos) {
		
		leftPID.setP(Constants.driveP);
		leftPID.setI(Constants.driveI);
		leftPID.setD(Constants.driveD);
		leftPID.setOutputRange(-1, 1);
		leftPID.setReference(pos, ControlType.kPosition);
	}

}
