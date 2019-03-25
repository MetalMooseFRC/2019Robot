/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class CargoSequence extends CommandGroup {

  public CargoSequence(double height, boolean isCargoShip) {

    if (!isCargoShip) {
      addSequential(new WaitForLineUp());
      addSequential(new DrivetrainDriveDistance(-4), 0.5);
    }
    //go up
    addSequential(new ElevatorToHeight(height), 1);

    if (!isCargoShip) {
      addSequential(new CollectorArmToPosition(-1500, 0.5));
      addSequential(new CollectorOuttake(0.4));
    }

  }

}
