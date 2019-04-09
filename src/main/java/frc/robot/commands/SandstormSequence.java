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

public class SandstormSequence extends CommandGroup {

  public SandstormSequence(double time1, double turn1, double time2, double turn2) {
    //wait for driver, just in case we need to coordinate with other robots
    addSequential(new WaitForLineUp());
    
    //drive, turn, drive, turn
    addSequential(new DrivetrainDriveTime(time1, -0.4));
    addSequential(new DrivetrainDriveAngle(turn1));
    addSequential(new DrivetrainDriveTime(time2, -0.4));
    addSequential(new DrivetrainDriveAngle(turn2));

  }

}
