/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Drivetrain myDrivetrain = new Drivetrain();
	public static final Elevator myElevator = new Elevator();
	public static final Collector myCollector = new Collector();
	public static final Lifter myLifter = new Lifter();
	public static final Arm myArm = new Arm();
	public static OI myOI;

	private SendableChooser<Integer> operatorBoardChooser = new SendableChooser<>();

	public static final SerialPort jevoisSerial = new SerialPort(115200, SerialPort.Port.kMXP);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		myOI = new OI();
		
		//Start stream for axis camera
		//CameraServer.getInstance().addAxisCamera("10.13.91.11");
		CameraServer.getInstance().startAutomaticCapture();

		//Start stream for jevois camera
	/** 	UsbCamera jevoisCamera = new UsbCamera("VisionCam", 0);
		jevoisCamera.setVideoMode(PixelFormat.kYUYV, 640, 480, 15);
		MjpegServer jevoisServer = new MjpegServer("VisionServer", 1180);
		jevoisServer.setSource(jevoisCamera);  */


		operatorBoardChooser.addDefault("Button Pad", 0);
		operatorBoardChooser.addObject("Logitech Controller", 1);
		SmartDashboard.putData("Operator Board", operatorBoardChooser);

		SmartDashboard.putData(myDrivetrain);
		SmartDashboard.putData(myElevator);
		SmartDashboard.putData(myCollector);
		SmartDashboard.putData(myLifter);

		SmartDashboard.putNumber("Elevator P", Constants.elevatorP);
		SmartDashboard.putNumber("Elevator I", Constants.elevatorI);
		SmartDashboard.putNumber("Elevator D", Constants.elevatorD);
		SmartDashboard.putNumber("Elevator Margin of Error", Constants.PIDDriveErrorMargin);

		SmartDashboard.putNumber("Vision P", Constants.visionP);
		SmartDashboard.putNumber("Vision I", Constants.visionI);
		SmartDashboard.putNumber("Vision Margin of Error", Constants.visionMargin);

		//SmartDashboard.putBoolean("Offense Mode", Constants.isOffenseMode);

		SmartDashboard.putNumber("Arm P", Constants.armP);
		SmartDashboard.putNumber("Arm I", Constants.armI);
		SmartDashboard.putNumber("Arm D", Constants.armD);
		SmartDashboard.putNumber("Arm Margin of Error", Constants.armMargin);

		SmartDashboard.putNumber("Port Hole 1", Constants.port1Height);
		SmartDashboard.putNumber("Port Hole 2", Constants.port2Height);
		SmartDashboard.putNumber("Port Hole 3", Constants.port3Height);
		SmartDashboard.putNumber("Hatch Hole 1", Constants.hatch1Height);
		SmartDashboard.putNumber("Hatch Hole 2", Constants.hacth2Height);
		SmartDashboard.putNumber("Hatch Hole 3", Constants.hacth3Height);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//System.out.println(jevoisSerial.readString());

		//Update values from SmartDashboard
		Constants.elevatorP = SmartDashboard.getNumber("Elevator P", 0);
		Constants.elevatorI = SmartDashboard.getNumber("Elevator I", 0);
		Constants.elevatorD = SmartDashboard.getNumber("Elevator D", 0);

		Constants.visionP = SmartDashboard.getNumber("Vision P", 0);
		Constants.visionI = SmartDashboard.getNumber("Vision I", 0);

		Constants.armP = SmartDashboard.getNumber("Arm P", 0);
		Constants.armI = SmartDashboard.getNumber("Arm I", 0);
		Constants.armD = SmartDashboard.getNumber("Arm D", 0);

		Constants.port1Height = SmartDashboard.getNumber("Port Hole 1", 0);
		Constants.port2Height = SmartDashboard.getNumber("Port Hole 2", 0);
		Constants.port3Height = SmartDashboard.getNumber("Port Hole 3", 0);
		Constants.hatch1Height = SmartDashboard.getNumber("Hatch Hole 1", 0);
		Constants.hacth2Height = SmartDashboard.getNumber("Hatch Hole 2", 0);
		Constants.hacth3Height = SmartDashboard.getNumber("Hatch Hole 3", 0);


		Constants.operatorBoardMode = operatorBoardChooser.getSelected();



	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
