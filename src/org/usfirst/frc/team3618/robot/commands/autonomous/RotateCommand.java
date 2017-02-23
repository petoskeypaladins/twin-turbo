package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RotateCommand extends Command {
	
	double targetAngle;
	boolean finished;

    public RotateCommand(double targetAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.driveSubsystem);
    	this.targetAngle = targetAngle;
    	finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double MAX_ERROR = 5;
    	double currentAngle = Robot.getRobotAngle();
    	double error = targetAngle - currentAngle;
		try {
			int direction = (int) (error / Math.abs(error));
			final double SPEED = .25;
			((DriveSubsystem) Robot.driveSubsystem).driveTank(direction * SPEED, -direction * SPEED);
		} catch (Exception e) {
			e.printStackTrace();
			finished = true;
		}
		finished = error < MAX_ERROR;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	((DriveSubsystem) Robot.driveSubsystem).stopMe();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
