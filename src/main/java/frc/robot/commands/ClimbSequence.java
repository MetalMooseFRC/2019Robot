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

     //Go up with different timeouts for each level
     if (HABLevel == 3) {addSequential(new ElevatorToHeight(height1), 1.75); }
     else if (HABLevel == 1) {addSequential(new ElevatorToHeight(height1), 0.7);}
     else if (HABLevel == 2) {addSequential(new ElevatorToHeight(height1), 1.5);}

    //arm all the way out
     addSequential(new CollectorArmToPosition(-2400, 0.8), 0.7);

     //Start climbing after the elevator hits the HAB
     addSequential(new ElevatorToHeight(height2), 0.6);
     addSequential(new Climb(liftHeight));
     //drive on
     addSequential(new DrivetrainDriveTime(0.5, -0.2));
     
     //drive forward while lifting the legs slightly to level out
     addParallel(new DrivetrainDriveTime(0.4, -0.08));
     if (HABLevel == 3) {addSequential(new LifterIn(108), 0.4);}
     else if (HABLevel == 1) {addSequential(new LifterIn(42), 0.4);}
     else if (HABLevel == 2) {addSequential(new LifterIn(90), 0.4);}
     
     
  }

}
