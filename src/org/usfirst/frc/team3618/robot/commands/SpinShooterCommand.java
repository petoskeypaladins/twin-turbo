package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.OI;
import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SpinShooterCommand extends Command {
	
    public SpinShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = (OI.operatorJoystick.getThrottle() + 1) / 2;
    	final double MAX_SHOOTER_SPEED = 1;
    	final double MIN_VALUE = .5;
    	double shooterSpeed = MAX_SHOOTER_SPEED - (throttle * (1-MIN_VALUE));
    	((ShooterSubsystem) Robot.shooterSubsystem).setPower(shooterSpeed);
    	System.out.println("spinning shooter at: " + shooterSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	((ShooterSubsystem) Robot.shooterSubsystem).setSpeed(0);
//    	Robot.shooterSubsystem.setBallIndexSolenoid(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
