package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftGearCommand extends Command {
    private boolean initialClampPistonState;
    private int loops;
    final int MAX_LOOPS = 30;

    public LiftGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.gearLiftSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        initialClampPistonState = Robot.gearLiftSubsystem.getClampState();
        Robot.gearLiftSubsystem.setClampPiston(false);
		Robot.gearLiftSubsystem.toggleLift();
		loops = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        loops++;
        System.out.println(loops);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return loops > MAX_LOOPS;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearLiftSubsystem.setClampPiston(initialClampPistonState);
    	System.out.println("end lift gear");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
