/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Interface between the PIDController object and the other robot code.
 */
public class BlankPIDOutput implements PIDOutput {    
    // Saves the output of the PIDController
    private double output;

    public BlankPIDOutput() {
        output = 0;
    }

    // Writes to output so it can be read
    public void pidWrite(double output) {
        this.output = output;
    }
}

