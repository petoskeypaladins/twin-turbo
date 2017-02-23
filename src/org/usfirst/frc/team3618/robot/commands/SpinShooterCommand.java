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
	
	boolean lastRunPhotoSensor; //true is a ball is recognized

    public SpinShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastRunPhotoSensor = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		final double MAX_SHOOTER_RPM = 5000;
    	double throttle = (OI.operatorJoystick.getThrottle() + 1) / 2;
    	boolean thisRunPhotoSensor = ((ShooterSubsystem) Robot.shooterSubsystem).getPhotoSensor();
    	final double MAX_SHOOTER_SPEED = 1;
    	final double MIN_VALUE = .5;
    	double shooterSpeed = MAX_SHOOTER_SPEED - (throttle * (1-MIN_VALUE));
    	double targetShooterSpeed = MAX_SHOOTER_RPM * shooterSpeed;
    	((ShooterSubsystem) Robot.shooterSubsystem).setSpeed(targetShooterSpeed);
//    	double currentShooterSpeed = ((ShooterSubsystem) Robot.shooterSubsystem).getSpeed();
//    	final double ALLOWABLE_ERROR = 300;
//    	double error = Math.abs(targetShooterSpeed - currentShooterSpeed);
//    	System.out.println("error: " + error);
//    	boolean ballIndexSolenoidOpen = !(error < ALLOWABLE_ERROR);
//    	System.out.println("Current Solenoid is: " + Robot.shooterSubsystem.getBallIndexSolenoid());
//    	System.out.println("Set Solenoid to: " + ballIndexSolenoidOpen);
//		Robot.shooterSubsystem.setBallIndexSolenoid(ballIndexSolenoidOpen);
//		System.out.println("Photo sensor: " + ((ShooterSubsystem) Robot.shooterSubsystem).getPhotoSensor());
//		if (!lastRunPhotoSensor && thisRunPhotoSensor) {
//			Robot.shooterSubsystem.setBallIndexSolenoid(true); //close ball index
//		}
//		lastRunPhotoSensor = thisRunPhotoSensor;
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
