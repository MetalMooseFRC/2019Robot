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

public class HatchPickUpSequence extends CommandGroup {

  public HatchPickUpSequence() {

    addSequential(new ArmsIn(), 0.5);

    addSequential(new WaitForLineUp());
    
    addParallel(new CollectorArmToPosition(-100, 0.4), 0.3);
    addSequential(new DrivetrainDriveTime(0.1, -0.3));
    
    addSequential(new ElevatorToHeight(5), 0.6);
    addSequential(new CollectorArmToPosition(0, -0.4));

    addSequential(new DrivetrainDriveTime(0.4, 0.15));
    addSequential(new ElevatorDown());

    //addSequential(new DrivetrainDriveAngle(0));
     
  }

}
