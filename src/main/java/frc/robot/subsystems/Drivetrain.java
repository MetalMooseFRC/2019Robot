/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainManualDrive;
import frc.robot.BlankPIDOutput;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	
	//Motor controllers
	public CANSparkMax rightFrontDriveMotor = new CANSparkMax(RobotMap.rightFrontDriveCANID, MotorType.kBrushless);
	private CANSparkMax rightMiddleDriveMotor = new CANSparkMax(RobotMap.rightMiddleDriveCANID, MotorType.kBrushless);
	private CANSparkMax rightBackDriveMotor = new CANSparkMax(RobotMap.rightBackDriveCANID, MotorType.kBrushless);

	public CANSparkMax leftFrontDriveMotor = new CANSparkMax(RobotMap.leftFrontDriveCANID, MotorType.kBrushless);
	private CANSparkMax leftMiddleDriveMotor = new CANSparkMax(RobotMap.leftMiddleDriveCANID, MotorType.kBrushless);
	private CANSparkMax leftBackDriveMotor = new CANSparkMax(RobotMap.leftBackDriveCANID, MotorType.kBrushless);

	private CANEncoder rightSparkMaxEncoder = new CANEncoder(rightFrontDriveMotor);
	private CANEncoder leftSparkMaxEncoder = new CANEncoder(leftFrontDriveMotor);
	CANPIDController rightPID = new CANPIDController(rightFrontDriveMotor);
	CANPIDController leftPID = new CANPIDController(leftFrontDriveMotor);

	//Reflective sensor
	//DigitalOutput reflectanceSensorPower = new DigitalOutput(RobotMap.reflectivePowerPort);
	AnalogInput reflectanceLeftOutsideSensor = new AnalogInput(0);
	AnalogInput reflectanceLeftInsideSensor = new AnalogInput(1);
	AnalogInput reflectanceRightInsideSensor = new AnalogInput(2);
	AnalogInput reflectanceRightOutsideSensor = new AnalogInput(3);

	
	//Gyro Sensors
	public AHRS myAHRS = new AHRS(SPI.Port.kMXP);
	public PIDController gyroPID = new PIDController(Constants.drivetrainGyroP, Constants.drivetrainGyroI, Constants.drivetrainGyroD, 0, myAHRS, new BlankPIDOutput());

	public void initDefaultCommand() {
		// Set the default command to manual driving
		setDefaultCommand(new DrivetrainManualDrive());
	}

	public Drivetrain() {
		//Set up gyro PID parameters
		gyroPID.setInputRange(-180, 180);
		gyroPID.setOutputRange(-0.5, 0.5);
		gyroPID.setAbsoluteTolerance(Constants.drivetrainGyroPIDError);
		gyroPID.setContinuous(true);

		resetEncoder();

		//Have one motor lead and the others follow
		rightMiddleDriveMotor.follow(rightFrontDriveMotor);
		rightBackDriveMotor.follow(rightFrontDriveMotor);

		leftMiddleDriveMotor.follow(leftFrontDriveMotor);
		leftBackDriveMotor.follow(leftFrontDriveMotor);
		
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

		setLeftSpeed(leftPower);
		
		setRightSpeed(rightPower);

	}

	public void throttledArcade(double speed, double turn) {
		double pos = Robot.myElevator.getEncoderCount();

		//Throttle drivetrain when elevator is up
		if (pos > Constants.elevatorLimit/4) {
			speed = speed/3;
			turn = turn/3;
		}

		arcadeDrive(speed, turn);
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

	public void resetEncoder() {
		rightSparkMaxEncoder.setPosition(0);
		leftSparkMaxEncoder.setPosition(0);
	}

	//Set PID reference for right side
	public void setRightPosition(double pos) {
		
		rightPID.setP(Constants.driveP);
		rightPID.setI(Constants.driveI);
		rightPID.setD(Constants.driveD);
		rightPID.setOutputRange(-1, 1);
		rightPID.setReference(pos, ControlType.kPosition);
	}

	//Set PID reference for left side
	public void setLeftPosition(double pos) {
		
		leftPID.setP(Constants.driveP);
		leftPID.setI(Constants.driveI);
		leftPID.setD(Constants.driveD);
		leftPID.setOutputRange(-1, 1);
		leftPID.setReference(pos, ControlType.kPosition);
	}

	public void setLeftSpeed(double speed) {
		leftFrontDriveMotor.set(speed);
	}

	public void setRightSpeed(double speed) {
		//Ivert right speed
		rightFrontDriveMotor.set(-speed);
	}

	//Get voltage of reflectance sensor pin 8
	public boolean getLeftOutReflectance() {
		//convert to boolean by threshhold
		return reflectanceLeftOutsideSensor.getVoltage() < Constants.reflectanceThreshHold;
	}

	//Get voltage of reflectance sensor pin 1
	public boolean getLeftInReflectance() {
		//convert to boolean by threshhold
		return reflectanceLeftInsideSensor.getVoltage() < Constants.reflectanceThreshHold;

	}

	//Get voltage of reflectance sensor pin 1
	public boolean getRightOutReflectance() {
		//convert to boolean by threshhold
		return reflectanceRightOutsideSensor.getVoltage() < Constants.reflectanceThreshHold;

	}

	//Get voltage of reflectance sensor pin 8
	public boolean getRightInReflectance() {
		//convert to boolean by threshhold
		return reflectanceRightInsideSensor.getVoltage() < Constants.reflectanceThreshHold;

	}

	//Use speed corrections to line up with lines
	public double correctLineFollowing(double speed, boolean isDoubleCorrected, boolean isReduced) {
		//Are we making this motor go faster or slower
		if (isReduced) {
			//Do we need to correct it more because it is farther off
			if (isDoubleCorrected) {
				speed *= Constants.lineFollowDoubleCorrection;
			} else {
				speed *= Constants.lineFollowCorrection;
			}
		} else {
			if (isDoubleCorrected) {
				speed *= (1 - Constants.lineFollowDoubleCorrection);
			} else {
				speed *= (1 - Constants.lineFollowCorrection);
			}
		}
		return speed;
	}

}
