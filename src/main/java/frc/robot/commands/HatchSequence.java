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

    if (!isCargoShip) {addSequential(new SetRocketHatch());}
    else {addSequential(new SetNotRocketHatch());}

    addSequential(new WaitForLineUp());
    
    if (isCargoShip) {
      addSequential(new ElevatorToHeight(height));
      addSequential(new CollectorArmToPosition(-500, 0.6), 0.2);
      addParallel(new CollectorArmToPosition(-2000, 0.6), 1.2);
      addSequential(new DrivetrainDriveTime(0.7, 0.2));

    } else {
    //only drive forward for highest hole
     if (height > 40) addSequential(new DrivetrainDriveDistance(-2), 1);

     addSequential(new ElevatorToHeight(height));
     addSequential(new CollectorArmToPosition(-1600, 0.5), 1.2);
     //Go slower if high up
     if (height < 5) { addSequential(new DrivetrainDriveTime(0.4, 0.25)); }
     else {addSequential(new DrivetrainDriveTime(0.5, 6/height));}
     
    }

    addSequential(new CollectorArmToPosition(0, -0.5), 0.5);
    addSequential(new ElevatorDown(), 0.8);
    
    addSequential(new SetNotRocketHatch());
    //Constants.isRocketHatch = false;

    /**
     //Go up and extend arm
     addSequential(new ElevatorToHeight(height));
     addSequential(new CollectorArmToPosition(-1500, 0.5), 1.2);

     //Drive back a bit then retract all parts
     addSequential(new DrivetrainDriveDistance(-6), 2.2);
     addSequential(new CollectorArmToPosition(0, -0.5));
     addSequential(new ElevatorDown());
     */

  }

}
