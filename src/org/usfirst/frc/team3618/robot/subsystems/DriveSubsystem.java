package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.DriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	protected RobotDrive robotDrive;
	protected final CANTalon frontLeftMotor = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
	protected final CANTalon rearLeftMotor = new CANTalon(RobotMap.REAR_LEFT_DRIVE);
	protected final CANTalon frontRightMotor = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
	protected final CANTalon rearRightMotor = new CANTalon(RobotMap.REAR_RIGHT_DRIVE);
	
	boolean finishedCurve = false;
	
	public DriveSubsystem() {
		robotDrive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		robotDrive.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void driveMecanum(double magnitude, double direction, double rotation) {
    	robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
		SmartDashboard.putNumber("Rotation", rotation);
		SmartDashboard.putNumber("Magnitude", magnitude);
		SmartDashboard.putNumber("Direction", direction);

    }
    
    // 1 is right, -1 is left
    public void strafe(double speed) {
    	double kP = .1;
    	double magnitude = Math.abs(speed);
    	double direction = (speed != 0 ? speed / Math.abs(speed) : 0) * 90;
    	double rotation = -Robot.getRobotAngle() * kP;
    	robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    	System.out.println(speed);
    }
    
    public void driveTank(double leftValue, double rightValue) {
    	robotDrive.tankDrive(leftValue, rightValue);
    	SmartDashboard.putNumber("Left Value", leftValue);
    	SmartDashboard.putNumber("Right Value", rightValue);
    }
    
    public void driveArcade(double moveValue, double rotateValue) {
    	double left = moveValue - rotateValue;
    	double right = moveValue + rotateValue;
    	robotDrive.tankDrive(left, -right);
//    	System.out.printf("left: %-20s right: %-20s move: %-20s rotate: %-20s \n", left, right, moveValue, rotateValue);
    }
    
    public void driveDistance(double distance, double time) {
//    	frontRightMotor.set(targetCounts);
    	double targetCounts = inchesToCounts(distance);
    	double currentCounts = frontLeftMotor.getEncPosition();
    	double proportional = 6000;
    	double speed = (targetCounts - currentCounts) / proportional;
    	final double MAX_SPEED = .6;
    	final double MIN_SPEED = .325;
    	try {
    		double direction = speed / Math.abs(speed);
    		speed = Math.abs(speed) > MAX_SPEED ? direction * MAX_SPEED : speed;
    		speed = Math.abs(speed) < MIN_SPEED ? direction * MIN_SPEED : speed;
    		final double RAMP_TIME = .7;
    		if (time < RAMP_TIME) {
    			speed = speed * time / RAMP_TIME;
    		}
    		driveStraightGyro(speed);
    	} catch (Exception e) {
    		
    	}
    	SmartDashboard.putNumber("speed", speed);
    	System.out.printf("Target Counts: %-20s Current Counts: %-20s Speed: %-20s\n", targetCounts, currentCounts, speed);
    }
    
    
    public void driveCurve(double radius) {
    	double arcLength = (Math.PI /3) * radius;
    	double targetCounts = inchesToCounts(arcLength);
    	double currentCounts = frontLeftMotor.getEncPosition();
    	double proportional = 6000;
    	double speed = (targetCounts - currentCounts) / proportional;
    	final double MAX_SPEED = .6;
    	final double MIN_SPEED = .325;
    	try {
    		double direction = speed / Math.abs(speed);
    		speed = Math.abs(speed) > MAX_SPEED ? direction * MAX_SPEED : speed;
    		speed = Math.abs(speed) < MIN_SPEED ? direction * MIN_SPEED : speed;
    		driveStraightGyro(speed);
    	} catch (Exception e) {
    		
    	}
    	SmartDashboard.putNumber("speed", speed);
    	System.out.printf("Target Counts: %-20s Current Counts: %-20s Speed: %-20s\n", targetCounts, currentCounts, speed);
    }
    
    public boolean getFinishedCurve() {
    	return finishedCurve;
    }
    
    public double getDistance() {
    	return countsToInches(frontLeftMotor.getEncPosition());
    }
    
    public void resetEncoder() {
    	frontLeftMotor.setEncPosition(0);
    }
    
    public int inchesToCounts(double inches) {
    	final double CPI = 356.4705882353;
    	return (int) (inches * CPI);
    }
    
    public double countsToInches(double counts) {
    	final double CPI = 356.4705882353;
    	return (counts / CPI);
    }
    
    public void driveStraight(double speed) {
    	robotDrive.tankDrive(speed+.005, -speed);
    }
    
    public void driveStraightGyro(double speed) {
    	double kP = .1;
    	driveArcade(speed, Robot.getRobotAngle()*kP);
    }
    
    public void stopMe() {
    	robotDrive.tankDrive(0, 0);
    }
}

