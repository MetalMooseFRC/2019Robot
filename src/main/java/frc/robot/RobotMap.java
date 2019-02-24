/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  	/**JOYSTICKS */
  public static final int driveStickPort = 0;

  /**AXIS */
  	//twistaxis
  public static final int driveStickZAxisPort = 2;

  //POV Logitech Controller axis
  public static final int POVAxisPort = 6;

  //Elevator up and down axis
  public static final int elevatorAxisPort = 1;
  //Elevator side to side axis
  public static final int elevatorXAxisPort = 0;

  //Arm ports for each operator board
  public static final int armAxisAuxPort = 5;
  public static final int armAxisPort = 1;
  
  /**BUTTONS */
  public static final int elevatorVisionCalibratePort = 1;

    /**Operators */
  public static final int operatorLeftPort = 2;
  public static final int operatorRightPort = 3;
//auxilary
  public static final int operatorControllerPort = 1;



	/**DRIVETRAIN */
  //Drivetrain motor ports
  //0 is unconfigured for Spark MAX via firmware v1.1.31
  public static final int rightFrontDriveCANID = 10;
  public static final int rightMiddleDriveCANID = 1;
  public static final int rightBackDriveCANID = 2;

  public static final int leftFrontDriveCANID = 3;
  public static final int leftMiddleDriveCANID = 4;
  public static final int leftBackDriveCANID = 5;

  //Reflectance sensor
  public static final int reflectivePowerPort = 9;
  public static final int reflectiveLeftSensorPort = 1;
  public static final int reflectiveRightSensorPort = 0;

  /**ELEVATOR */
  public static final int elevatorRightMotorCANID = 6; 
  public static final int elevatorLeftMotorCANID = 7; 
  public static final int elevatorXMotorCANID = 2;

  public static final int potAnalogPin = 0;

  /**COLLECTER */
  public static final int collectorMotorCANID = 0;
  public static final int collectorArmMotorCANID = 1;

}