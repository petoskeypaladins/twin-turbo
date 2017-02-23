package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightCommand extends Command {
	
	private double distance;
	private Timer timer = new Timer();

    public DriveStraightCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double SPEED = .4;
    	((DriveSubsystem) Robot.driveSubsystem).driveStraight(SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentDistance = timeToDistance(timer.get());
        return currentDistance >= distance;
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
    
    private double timeToDistance(double time) {
    	final double RATIO = 1;
    	double distance = time * RATIO;
    	return distance;
    }
}
