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

    public DriveStraightCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	((DriveSubsystem) Robot.driveSubsystem).resetEncoder();
    	Robot.resetRobotAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	((DriveSubsystem) Robot.driveSubsystem).driveDistance(distance, this.timeSinceInitialized());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	final double MAX_ERR = 1;
    	double currentDistance = ((DriveSubsystem) Robot.driveSubsystem).getDistance();
    	double error = distance - currentDistance;
    	System.out.printf("error: %-20s distance: %-20s\n", error, currentDistance);
        return Math.abs(error) <= MAX_ERR;
    }

    // Called once after isFinished returns true
    protected void end() {
    	((DriveSubsystem) Robot.driveSubsystem).stopMe();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("TIMER INTERRUPT");
    	end();
    }
}
