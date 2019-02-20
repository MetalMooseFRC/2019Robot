/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  //Driver Logitech joystick
  public static final Joystick driverStick = new Joystick(RobotMap.driveStickPort);
  
  public static final Button elevatorVisionCalibrateButton = new JoystickButton(driverStick, RobotMap.elevatorVisionCalibratePort);
                             
  //Operator button pad
  public static final Joystick operatorStick = new Joystick(RobotMap.operatorPort);

  //temporary mapping
  public static final Button zeroElevatorXButton = new JoystickButton(operatorStick, 2),
                             outButton = new JoystickButton(operatorStick, 3),
                             inButton = new JoystickButton(operatorStick, 4),
                             rocketHatch1Button = new JoystickButton(operatorStick, 5),
                             rocketHatch2Button = new JoystickButton(operatorStick, 6),
                             rocketHatch3Button = new JoystickButton(operatorStick, 7),
                             rocketPort1Button = new JoystickButton(operatorStick, 8),
                             rocketPort2Button = new JoystickButton(operatorStick, 9),
                             rocketPort3Button = new JoystickButton(operatorStick, 10);


  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public OI() {
    elevatorVisionCalibrateButton.whenPressed(new ElevatorXToVision());

    zeroElevatorXButton.whenPressed(new ElevatorXToPosition(0));
  }
}

