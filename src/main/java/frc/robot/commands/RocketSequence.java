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
    //Go up and extend arm
     addSequential(new ElevatorToHeight(height));
     addSequential(new CollectorArmToPosition(-1500, 0.5), 1.2);

     //Drive back a bit then retract all parts
     addSequential(new DrivetrainDriveDistance(-6), 2.2);
     addSequential(new CollectorArmToPosition(0, -0.5));
     addSequential(new ElevatorDown());

  }

}
