package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PlaceGearCommand extends Command {
	
	private enum GearStep {
		kHorizontalAlign,
		kForwardMove,
		kUnclamp,
		kBackwardMove;
	}

	GearStep currentGearStep;
	boolean finished;
	
    public PlaceGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.driveSubsystem);
    	finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.driveSubsystem.setShiftSolenoid(true);
		currentGearStep = GearStep.kHorizontalAlign;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double magnitude = .35;
		double direction = 0;
		final double rotation = 0;
		switch (currentGearStep) {
		case kHorizontalAlign:
			double dCx = Robot.dCx;
			dCx = (Math.abs(dCx) < 5) ? 0 : dCx;
			if (dCx < 0) {
				direction = -90;
			} else if (dCx > 0) {
				direction = 90;
			} else {
				currentGearStep = GearStep.kForwardMove;
				magnitude = 0;
			}
			((DriveSubsystem) Robot.driveSubsystem).driveMecanum(magnitude, direction, rotation);
			break;
		case kForwardMove:
			Timer forwardTimer = new Timer();
			forwardTimer.start();
			final double forwardDriveTime = 1;
			if (forwardTimer.get() >= forwardDriveTime) {
				currentGearStep = GearStep.kUnclamp;
				magnitude = 0;
			}
			((DriveSubsystem) Robot.driveSubsystem).driveMecanum(magnitude, direction, rotation);
		case kUnclamp:
			Robot.gearLiftSubsystem.setClampPiston(true);
			currentGearStep = GearStep.kBackwardMove;
			break;
		case kBackwardMove:
			Timer backwardTimer = new Timer();
			backwardTimer.start();
			final double backwardDriveTime = 1;
			if (backwardTimer.get() >= backwardDriveTime){
				finished = true;
			}
		default:
			break;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
