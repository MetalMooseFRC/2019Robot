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

public class ClimbSequence extends CommandGroup {

  public ClimbSequence(double height1, double height2, double liftHeight) {
    //ignore confirmation
     addSequential(new ConfirmLineUp());

     addSequential(new ElevatorToHeight(height1));
     addSequential(new CollectorArmToPosition(-2350));
     addSequential(new ElevatorToHeight(height2));
     addSequential(new Climb(liftHeight));
     //addSequential(new DrivetrainDriveDistance(2));
  }

}
