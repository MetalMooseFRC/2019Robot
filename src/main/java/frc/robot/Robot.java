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

import org.opencv.video.Video;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.commands.SandstormSequence;
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
	private SendableChooser<Integer> startingPositionChooser = new SendableChooser<>();
	private SendableChooser<Integer> startingCommandChooser = new SendableChooser<>();

	Command autoCommand;

	public static UsbCamera bottomCam;
	public static UsbCamera topCam;
	public static VideoSink cameraServer;

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

		//start USB cameras
		bottomCam = CameraServer.getInstance().startAutomaticCapture(0);
		topCam = CameraServer.getInstance().startAutomaticCapture(1);

		//Start stream for jevois camera
	/** 	UsbCamera jevoisCamera = new UsbCamera("VisionCam", 0);
		jevoisCamera.setVideoMode(PixelFormat.kYUYV, 640, 480, 15);
		MjpegServer jevoisServer = new MjpegServer("VisionServer", 1180);
		jevoisServer.setSource(jevoisCamera);  */

		//Smart Dashboard widgets
		operatorBoardChooser.addDefault("Button Pad", 0);
		operatorBoardChooser.addObject("Logitech Controller", 1);
		SmartDashboard.putData("Operator Board", operatorBoardChooser);

		startingPositionChooser.addDefault("Manual", 0);
		startingPositionChooser.addObject("Left", 1);
		startingPositionChooser.addObject("Middle", 2);
		startingPositionChooser.addObject("Right", 3);
		SmartDashboard.putData("Starting Position", startingPositionChooser);

		startingCommandChooser.addObject("Left Rocket", 0);
		startingCommandChooser.addDefault("Left Cargo", 1);
		startingCommandChooser.addObject("Right Cargo", 2);
		startingCommandChooser.addObject("Right Rocket", 3);
		SmartDashboard.putData("Starting Command", startingCommandChooser);


		SmartDashboard.putData(myDrivetrain);
		SmartDashboard.putData(myElevator);
		SmartDashboard.putData(myCollector);
		SmartDashboard.putData(myLifter);

		SmartDashboard.putNumber("Elevator P", Constants.elevatorP);
		SmartDashboard.putNumber("Elevator I", Constants.elevatorI);
		SmartDashboard.putNumber("Elevator D", Constants.elevatorD);
		SmartDashboard.putNumber("Elevator Margin of Error", Constants.PIDDriveErrorMargin);

	/*	SmartDashboard.putNumber("Vision P", Constants.visionP);
		SmartDashboard.putNumber("Vision I", Constants.visionI);
		SmartDashboard.putNumber("Vision Margin of Error", Constants.visionMargin); */

		SmartDashboard.putNumber("Light Threshhold", Constants.reflectanceThreshHold);

		/** SmartDashboard.putNumber("Arm P", Constants.armP);
		SmartDashboard.putNumber("Arm I", Constants.armI);
		SmartDashboard.putNumber("Arm D", Constants.armD);
		SmartDashboard.putNumber("Arm Margin of Error", Constants.armMargin); */

		SmartDashboard.putNumber("Port Hole 1", Constants.port1Height);
		SmartDashboard.putNumber("Port Hole 2", Constants.port2Height);
		SmartDashboard.putNumber("Port Hole 3", Constants.port3Height);
		SmartDashboard.putNumber("Hatch Hole 1", Constants.hatch1Height);
		SmartDashboard.putNumber("Hatch Hole 2", Constants.hacth2Height);
		SmartDashboard.putNumber("Hatch Hole 3", Constants.hacth3Height);
		
	}


	@Override
	public void disabledInit() {
		myDrivetrain.myAHRS.reset();
		myArm.armEncoder.reset();
		myLifter.rightEncoder.setPosition(0);
		myLifter.leftEncoder.setPosition(0);

		Constants.isRocketHatch = false;
		Constants.isLinedUp = false;

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


	@Override
	public void autonomousInit() {

		Constants.startingPosition = startingPositionChooser.getSelected();
		Constants.startingCommand = startingCommandChooser.getSelected();

		//Left options: left rocket and left cargo
		if (Constants.startingPosition == 1) {
			if (Constants.startingCommand == 0) { autoCommand = new SandstormSequence(2, -90, 0.6, -30);}
			else if (Constants.startingCommand == 1) { autoCommand = new SandstormSequence(2, 90, 0.4, 0);}

		//Middle options: left cargo and right cargo
		} else if (Constants.startingPosition == 2) {
			if (Constants.startingCommand == 1) { autoCommand = new SandstormSequence(1.5, -90, 0.1, 0);}
			else if (Constants.startingCommand == 2) { autoCommand = new SandstormSequence(1.5, 90, 0.1, 0);}

		//Right options: right cargo and right rocket
		} else if (Constants.startingPosition == 3) {
			if (Constants.startingCommand == 2) { autoCommand = new SandstormSequence(2, -90, 0.4, 0);}
			else if (Constants.startingCommand == 3) { autoCommand = new SandstormSequence(2, 90, 0.6, 30);}

		}

		if (autoCommand != null) autoCommand.start();

	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
	}


	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//System.out.println(jevoisSerial.readString());

		//Update values from SmartDashboard
		Constants.elevatorP = SmartDashboard.getNumber("Elevator P", 0);
		Constants.elevatorI = SmartDashboard.getNumber("Elevator I", 0);
		Constants.elevatorD = SmartDashboard.getNumber("Elevator D", 0);

		/** Constants.visionP = SmartDashboard.getNumber("Vision P", 0);
		Constants.visionI = SmartDashboard.getNumber("Vision I", 0);

		Constants.armP = SmartDashboard.getNumber("Arm P", 0);
		Constants.armI = SmartDashboard.getNumber("Arm I", 0);
		Constants.armD = SmartDashboard.getNumber("Arm D", 0);
		*/

		Constants.reflectanceThreshHold = SmartDashboard.getNumber("Light Threshhold", 2.5);

		Constants.port1Height = SmartDashboard.getNumber("Port Hole 1", 0);
		Constants.port2Height = SmartDashboard.getNumber("Port Hole 2", 0);
		Constants.port3Height = SmartDashboard.getNumber("Port Hole 3", 0);
		Constants.hatch1Height = SmartDashboard.getNumber("Hatch Hole 1", 0);
		Constants.hacth2Height = SmartDashboard.getNumber("Hatch Hole 2", 0);
		Constants.hacth3Height = SmartDashboard.getNumber("Hatch Hole 3", 0);


		Constants.operatorBoardMode = operatorBoardChooser.getSelected();

		//System.out.println("ROcket Angle " + Constants.isRocketHatch);


	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
