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

public class RocketSequence extends CommandGroup {

  public RocketSequence(double height) {

     addSequential(new ElevatorToHeight(height));
     addSequential(new CollectorArmToPosition(-1500, 0.5));
     addSequential(new DrivetrainDriveDistance(-6));
     addSequential(new CollectorArmToPosition(0, -0.5));

  }

}
