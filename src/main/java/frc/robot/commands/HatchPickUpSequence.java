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

    //bring arms in
    addSequential(new ArmsIn(), 0.2);
    
    addSequential(new DrivetrainDriveTime(0.1, -0.3));
    
    //bringthe elevator up to avoid brushes
    addSequential(new ElevatorToHeight(5, true), 0.7);
    
    //secure velcro
    addSequential(new DrivetrainDriveTime(0.1, -0.2));
    
    //secure the arm in
    addSequential(new CollectorArmToPosition(0, -0.4), 0.2);

    //drive back and reset the elevator
    addSequential(new DrivetrainDriveTime(0.4, 0.15));
    addSequential(new ElevatorDown(), 0.5);
     
  }

}
