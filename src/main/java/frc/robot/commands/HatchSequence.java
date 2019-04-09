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

public class HatchSequence extends CommandGroup {

  public HatchSequence(double height, boolean isCargoShip) {

    //set the angle to search for
    if (!isCargoShip) {addSequential(new SetRocketHatch());}
    else {addSequential(new SetNotRocketHatch());}

    //wait for driver
    addSequential(new WaitForLineUp());
    
    if (isCargoShip) {
      //arm out at the same speed as the drivetrain
      addSequential(new CollectorArmToPosition(-700, 0.8), 0.4);
      addParallel(new CollectorArmToPosition(-2000, 0.8), 1.2);
      addSequential(new DrivetrainDriveTime(0.7, 0.15));

    } else {
    
      //elevator to height then push hatch onto rocket with arm
     addSequential(new ElevatorToHeight(height), 1.8);
     addSequential(new CollectorArmToPosition(-1600, 0.8), 0.5);

     addParallel(new CollectorArmToPosition(-2000, 0.9), 0.2);

     //drive back
      if (height == Constants.hatch1Height) { addSequential(new DrivetrainDriveTime(0.4, 0.25));}
      else if (height == Constants.hacth2Height) { addSequential(new DrivetrainDriveTime(0.5, 0.5));}
      else if (height == Constants.hacth3Height) { addSequential(new DrivetrainDriveTime(0.8, 0.4));}

     
    }

    //bring everything in and reset
    addSequential(new CollectorArmToPosition(0, -0.5), 0.5);
    addSequential(new ElevatorDown(), 1);
    
    addSequential(new SetNotRocketHatch());

  }

}
