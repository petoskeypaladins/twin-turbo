package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.OI;
import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorCommand extends Command {

    public static final int FORWARD = 1;
    public static final int BACKWARD = -1;
    private int direction;

	public AgitatorCommand(int direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.agitatorSubsystem);
    	this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double AGITATOR_SPEED = 1;
    	double speed = direction * AGITATOR_SPEED;
    	Robot.agitatorSubsystem.setSpeed(direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.agitatorSubsystem.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
