/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainManualDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	
	//Motor controllers
	public CANSparkMax rightDriveMotor = new CANSparkMax(RobotMap.rightDriveCANID, MotorType.kBrushless);
	private CANSparkMax leftDriveMotor = new CANSparkMax(RobotMap.leftDriveCANID, MotorType.kBrushless);

	private CANEncoder rightSparkMaxEncoder = new CANEncoder(rightDriveMotor);
	private DifferentialDrive myDifferentialDrive = new DifferentialDrive(leftDriveMotor, rightDriveMotor);


	public void initDefaultCommand() {
		// Set the default command to manual driving
		setDefaultCommand(new DrivetrainManualDrive());
	}

	//speed is forward or backwards speed
	//turn is the turning speed
	public void arcadeDrive(double speed, double turn) {
		myDifferentialDrive.arcadeDrive(speed, turn);
	}
	public double getEncoderCount() {
		return rightSparkMaxEncoder.getPosition();
	}
}
