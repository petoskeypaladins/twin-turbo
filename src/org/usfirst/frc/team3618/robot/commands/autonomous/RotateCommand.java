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
    	Robot.driveSubsystem.setShiftSolenoid(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	final double MAX_ERROR = 1;
    	double currentAngle = Robot.getRobotAngle();
    	double error = currentAngle - targetAngle ;
    	double proportional = 20;
    	final double MAX_SPEED = .6;
    	final double MIN_SPEED = .40;
		try {
    		double direction = error / Math.abs(error);
    		double speed = (error / proportional) + direction * MIN_SPEED;
    		speed = Math.abs(speed) > MAX_SPEED ? direction * MAX_SPEED : speed;
//    		speed = Math.abs(speed) < MIN_SPEED ? direction * MIN_SPEED : speed;
			((DriveSubsystem) Robot.driveSubsystem).driveArcade(0, speed);
			System.out.printf("currentAngle: %-20s targetAngle: %-20s speed: %-20s\n", currentAngle, targetAngle, speed);
		} catch (Exception e) {
			e.printStackTrace();
			finished = true;
		}
		finished = Math.abs(error) < MAX_ERROR;
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
