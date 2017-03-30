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
	Timer forwardTimer = new Timer();
	Timer backwardTimer = new Timer();
	Timer unclampWaitTimer = new Timer();
	
    public PlaceGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) Robot.driveSubsystem);
    	finished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.driveSubsystem.setShiftSolenoid(true);
		Robot.resetRobotAngle();
		currentGearStep = GearStep.kHorizontalAlign;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double speed = .4;
		double direction = 0;
		final double rotation = 0;
		switch (currentGearStep) {
		case kHorizontalAlign:
			double dCx = Robot.dCx;
			dCx = (Math.abs(dCx) < 10) ? 0 : dCx;
			if (dCx != 0) {
				double proportional = 260;
				final double MAX_SPEED = .6;
				final double MIN_SPEED = .36;
				try {
					direction = dCx / Math.abs(dCx);
					speed = -((dCx / proportional) + direction * MIN_SPEED);
					speed = Math.abs(speed) > MAX_SPEED ? direction * MAX_SPEED : speed;
				} catch (Exception e) {
					e.printStackTrace();
					forwardTimer.start();
					currentGearStep = GearStep.kForwardMove;
				}
			} else {
				currentGearStep = GearStep.kForwardMove;
				forwardTimer.start();
				speed = 0;
				System.out.println("FINISHED HORIZONTAL ALIGN");
			}
			((DriveSubsystem) Robot.driveSubsystem).strafe(speed);
			System.out.printf("dCx: %-20s Speed: %-20s\n", dCx, speed);
			break;
		case kForwardMove:
			final double forwardDriveTime = 1;
			if (forwardTimer.get() >= forwardDriveTime) {
				currentGearStep = GearStep.kUnclamp;
				unclampWaitTimer.start();
				System.out.println("STOPPED FORWARD");
				speed = 0;
			}
			System.out.println("forward time: " + forwardTimer.get());
			((DriveSubsystem) Robot.driveSubsystem).driveStraightGyro(.6);;
			break;
		case kUnclamp:
			final double unclampWaitTime = .5;
			if (unclampWaitTimer.get() >= unclampWaitTime){
				finished = true;
				System.out.println("STOPPED FORWARD");
				speed = 0;
			}
			Robot.gearLiftSubsystem.setClampPiston(true);
			currentGearStep = GearStep.kBackwardMove;
			backwardTimer.start();
			break;
		case kBackwardMove:
			final double backwardDriveTime = 1;
			if (backwardTimer.get() >= backwardDriveTime){
				finished = true;
				System.out.println("STOPPED FORWARD");
				speed = 0;
			}
//			System.out.println("backward time: " + forwardTimer.get());
			((DriveSubsystem) Robot.driveSubsystem).driveStraightGyro(-.6);
			break;
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
