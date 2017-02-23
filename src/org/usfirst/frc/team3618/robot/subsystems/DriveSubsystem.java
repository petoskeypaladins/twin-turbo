package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.DriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
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
    
    public void driveTank(double leftValue, double rightValue) {
    	robotDrive.tankDrive(leftValue, rightValue);
    	SmartDashboard.putNumber("Left Value", leftValue);
    	SmartDashboard.putNumber("Right Value", rightValue);
    }
    
    public void driveStraight(double speed) {
    	robotDrive.tankDrive(speed, speed);
    }
    
    public void stopMe() {
    	robotDrive.tankDrive(0, 0);
    }
}

