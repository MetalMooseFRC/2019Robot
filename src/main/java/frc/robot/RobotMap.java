/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class RobotMap {

    /**JOYSTICKS */
    //driver joystick
  public static final int driveStickPort = 0;

  /**AXIS */
  //twist axis for driver
  public static final int driveStickZAxisPort = 2;

  //Elevator up and down axis (left joystick)
  public static final int elevatorAxisPort = 1;
  //Elevator slower speed (left joystick)
  public static final int elevatorXAxisPort = 0;

  //Arm ports for each operator board (right joystick)
  public static final int armAxisAuxPort = 5;
  public static final int armAxisPort = 1;


    /**Operators */
  //button board ports
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

  //Reflectance sensor unused
  public static final int leftRelfectancePowerPort = 7;
  public static final int rightRelfectancePowerPort = 6;

  /**ELEVATOR */
  public static final int elevatorRightMotorCANID = 12; 
  public static final int elevatorLeftMotorCANID = 7; 

  /**COLLECTER */
  public static final int collectorMotorCANID = 0;
  public static final int collectorArmMotorCANID = 1;

  //Digital IO ports for arm encoder
  public static final int armEncoderAPort = 9;
  public static final int armEncoderBPort = 8;

  /**LIFTER */
  public static final int leftLifterMotorCANID = 11;
  public static final int rightLifterMotorCANID = 13;


}