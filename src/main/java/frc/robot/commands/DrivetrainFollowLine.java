package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;



/**
 * An example command.  You can replace me with your own command.
 */
public class DrivetrainFollowLine extends Command {
	boolean isLeftReflect;
	boolean isMiddleReflect;
	boolean isRightReflect;

	public DrivetrainFollowLine() {
		// Use requires() here to declare subsystem dependencies
        requires(Robot.myDrivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
    
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		/** isLeftReflect = Robot.myDrivetrain.getLeftReflectance();
		isMiddleReflect = Robot.myDrivetrain.getMiddleReflectance();
		isRightReflect = Robot.myDrivetrain.getRightReflectance();

		double speed = Math.abs(OI.driverStick.getY());

		if (isLeftReflect) {
		//turn left and double correct if the middle is not sensing
			Robot.myDrivetrain.setLeftSpeed(Robot.myDrivetrain.correctLineFollowing(speed, !isMiddleReflect, true));
			Robot.myDrivetrain.setRightSpeed(Robot.myDrivetrain.correctLineFollowing(speed, !isMiddleReflect, false));

		} else if (isRightReflect) {
		//turn right and double correct if the middle is not sensing
			Robot.myDrivetrain.setLeftSpeed(Robot.myDrivetrain.correctLineFollowing(speed, !isMiddleReflect, false));
			Robot.myDrivetrain.setRightSpeed(Robot.myDrivetrain.correctLineFollowing(speed, !isMiddleReflect, true));
			
		} */
	
       
	}

	//Don't know what to use to consider finished, to be determined
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
       return (isLeftReflect && isRightReflect && isMiddleReflect) || (!isLeftReflect && !isRightReflect && !isMiddleReflect);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
       System.out.println("Lined up");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
