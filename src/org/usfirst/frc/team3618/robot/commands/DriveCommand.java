package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.OI;
import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean isMecanum = Robot.driveSubsystem.getShiftSolenoidState();
    	if (isMecanum) {
			double magnitude = getMagnitude();
			double direction = getDirection();
			double rotation = OI.driveController.getRawAxis(4);
			magnitude = Math.pow(magnitude, 3);
			rotation = Math.pow(rotation, 3);
			
			((DriveSubsystem) Robot.driveSubsystem).driveMecanum(magnitude, direction, rotation);
    	} else {
    		double leftValue = -OI.driveController.getY(Hand.kLeft);
    		double rightValue = OI.driveController.getY(Hand.kRight);
    		
    		leftValue = Math.abs(leftValue) < .15 ? 0 : leftValue;
    		rightValue = Math.abs(rightValue) < .15 ? 0 : rightValue;
    		
    		((DriveSubsystem) Robot.driveSubsystem).driveTank(leftValue, rightValue);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	((DriveSubsystem) Robot.driveSubsystem).stopMe();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	((DriveSubsystem) Robot.driveSubsystem).stopMe();
    }
    
    private double getMagnitude() {
    	double x = OI.driveController.getRawAxis(0);
    	double y = OI.driveController.getRawAxis(1);
    	return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    private double getDirection() {
    	double x = OI.driveController.getRawAxis(0);
    	double y = OI.driveController.getRawAxis(1);
    	return Math.toDegrees(Math.atan2(x, -y));
    }
}
