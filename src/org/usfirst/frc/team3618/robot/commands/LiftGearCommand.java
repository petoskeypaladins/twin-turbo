package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftGearCommand extends Command {

    public LiftGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.gearLiftSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.gearLiftSubsystem.toggleLift();
		Robot.gearLiftSubsystem.setClampPiston(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
