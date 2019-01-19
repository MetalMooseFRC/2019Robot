package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;



/**
 * An example command.  You can replace me with your own command.
 */
public class DrivetrainFollowLine extends Command {
	boolean isLeftReflect;
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
		isLeftReflect = Robot.myDrivetrain.getLeftReflectance();
		isRightReflect = Robot.myDrivetrain.getRightReflectance();

		if (isLeftReflect && !isRightReflect) {
			//turn left is positive
			Robot.myDrivetrain.arcadeDrive(Constants.lineFollowSpeed, Constants.lineFollowSpeed);

		} else if (!isLeftReflect && isRightReflect) {
			Robot.myDrivetrain.arcadeDrive(Constants.lineFollowSpeed, -Constants.lineFollowSpeed);

		} else if (isLeftReflect && isRightReflect) {
			Robot.myDrivetrain.arcadeDrive(Constants.lineFollowSpeed, 0);

		} else {
			Robot.myDrivetrain.arcadeDrive(0,0);
		}
       
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
       return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
       System.out.println("ended");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
