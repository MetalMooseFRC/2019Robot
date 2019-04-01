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

  public ClimbSequence(double height1, double height2, double liftHeight, int HABLevel) {

     //Go up and extend arm
     addSequential(new ElevatorToHeight(height1), 1.75);
     addSequential(new CollectorArmToPosition(-2400, 0.8), 0.5);

     //Start climbing after the elevator hits the HAB
     addSequential(new ElevatorToHeight(height2), 0.6);
     addSequential(new Climb(liftHeight, HABLevel));
     //addSequential(new DrivetrainDriveTime(3, -0.2));
     
     //addSequential(new LifterIn(0));
     
  }

}
