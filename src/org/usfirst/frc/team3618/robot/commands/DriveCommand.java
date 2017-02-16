package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.OI;
import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean isMecanum = Robot.driveSubsystem.getShiftSolenoidState();
    	if(OI.driveController.getAButton()) {
    		Robot.driveSubsystem.setShiftSolenoid(true);
    		double magnitude;
    		double direction;
    		final double rotation = 0;
    		double dCx = Robot.dCx;
    		dCx = (Math.abs(dCx) < 5) ? 0 : dCx;
    		magnitude = .45;
    		direction = dCx < 0 ? -90 : 90;
    		if (dCx < 0) {
    			direction = -90;
    		} else if (dCx > 0) {
    			direction = 90;
    		} else {
    			direction = 0;
    		}
    		Robot.driveSubsystem.driveMecanum(magnitude, direction, rotation);
    	} else if (isMecanum) {
			double magnitude = getMagnitude();
			double direction = getDirection();
			double rotation = OI.driveController.getRawAxis(4);
			
			magnitude = Math.abs(magnitude) < .15 ? 0 : magnitude;
			rotation = Math.abs(rotation) < .15 ? 0 : rotation;
			
			Robot.driveSubsystem.driveMecanum(magnitude, direction, rotation);
    	} else {
    		double leftValue = -OI.driveController.getY(Hand.kLeft);
    		double rightValue = OI.driveController.getY(Hand.kRight);
    		
    		leftValue = Math.abs(leftValue) < .15 ? 0 : leftValue;
    		rightValue = Math.abs(rightValue) < .15 ? 0 : rightValue;
    		
    		Robot.driveSubsystem.driveTank(leftValue, rightValue);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stopMe();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.stopMe();
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
