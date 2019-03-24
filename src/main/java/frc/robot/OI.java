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

  //Driver Logitech joystick
  public static final Joystick driverStick = new Joystick(RobotMap.driveStickPort);

  public static final Button armOutButton = new JoystickButton(driverStick, 9),
                             armInButton = new JoystickButton(driverStick, 11),
                             slowerDriveButton = new JoystickButton(driverStick, 1),
                             lifterOutButton = new JoystickButton(driverStick, 10),
                             lifterInButton = new JoystickButton(driverStick, 12),
                             confirmLineUpButton = new JoystickButton(driverStick, 2);
                               
  //Operator button pad
  public static final Joystick operatorLeftPad = new Joystick(RobotMap.operatorLeftPort);
  public static final Joystick operatorRightPad = new Joystick(RobotMap.operatorRightPort);
  public static final Joystick operatorController = new Joystick(RobotMap.operatorControllerPort);



  //Button Pad
  public static final Button zeroElevatorXButton = new JoystickButton(operatorLeftPad, 5),
                             outButton = new JoystickButton(operatorRightPad, 12),
                             inButton = new JoystickButton(operatorRightPad, 11),
                             rocketHatch1Button = new JoystickButton(operatorLeftPad, 7),
                             rocketHatch2Button = new JoystickButton(operatorLeftPad, 8),
                             rocketHatch3Button = new JoystickButton(operatorLeftPad, 9),
                             rocketPort1Button = new JoystickButton(operatorLeftPad, 10),
                             rocketPort2Button = new JoystickButton(operatorLeftPad, 11),
                             rocketPort3Button = new JoystickButton(operatorLeftPad, 12),
                             HAB3ClimbButton = new JoystickButton(operatorRightPad, 8),
                             HAB2ClimbButton = new JoystickButton(operatorRightPad, 9),
                             HAB23ClimbButton = new JoystickButton(operatorRightPad, 10),
                             cargoShipButton = new JoystickButton(operatorLeftPad, 5),
                             abortButton = new JoystickButton(operatorLeftPad, 6);


  //auxilary controller buttons
  public static final Button zeroElevatorXButtonAux = new JoystickButton(operatorController, 1),
                             outButtonAux = new JoystickButton(operatorController, 6),
                             inButtonAux  = new JoystickButton(operatorController, 5),
                             rocketHatchButton = new JoystickButton(operatorController, 3),
                             rocketPortButton = new JoystickButton(operatorController, 2);

  
  public OI() {

   /**  if (Constants.operatorBoardMode == 1) {
      //Depending on POV state, react differently when elevatorpreset button is pressed
      if (operatorController.getRawAxis(RobotMap.POVAxisPort) == 0 && rocketHatchButton.get()) {
        new ElevatorToHeight(Constants.hacth2Height);
      } else if (operatorController.getRawAxis(RobotMap.POVAxisPort) == 1 && rocketHatchButton.get()) {
        new ElevatorToHeight(Constants.hacth3Height);
      } else if (operatorController.getRawAxis(RobotMap.POVAxisPort) == -1 && rocketHatchButton.get()) {
        new ElevatorToHeight(Constants.hatch1Height);
      } 

      if (operatorController.getRawAxis(RobotMap.POVAxisPort) == 0 && rocketPortButton.get()) {
        new ElevatorToHeight(Constants.port2Height);
      } else if (operatorController.getRawAxis(RobotMap.POVAxisPort) == 1 && rocketPortButton.get()) {
        new ElevatorToHeight(Constants.port3Height);
      } else if (operatorController.getRawAxis(RobotMap.POVAxisPort) == -1 && rocketPortButton.get()) {
        new ElevatorToHeight(Constants.port1Height);
      }  */

      //zeroElevatorXButtonAux.whenPressed(new ElevatorXToPosition(0));

   // } else {
      //zeroElevatorXButton.whenPressed(new ElevatorXToPosition(0));
      

      rocketHatch1Button.whenPressed(new RocketSequence(Constants.hatch1Height));
      rocketHatch2Button.whenPressed(new RocketSequence(Constants.hacth2Height));
      rocketHatch3Button.whenPressed(new RocketSequence(Constants.hacth3Height));

      rocketPort1Button.whenPressed(new CargoSequence(Constants.port1Height, false));
      rocketPort2Button.whenPressed(new CargoSequence(Constants.port2Height, false));
      rocketPort3Button.whenPressed(new CargoSequence(Constants.port3Height, false));

      cargoShipButton.whenPressed(new CargoSequence(Constants.cargoShipHeight, true));

      abortButton.whenPressed(new AbortAuto());

      confirmLineUpButton.whenPressed(new ConfirmLineUp());
      HAB3ClimbButton.whenPressed(new ClimbSequence(15, 11.1, 230));
      HAB2ClimbButton.whenPressed(new ClimbSequence(6, 3.2, 115));
      HAB23ClimbButton.whenPressed(new ClimbSequence(10, 8, 190));
      

    //}
  }
}

