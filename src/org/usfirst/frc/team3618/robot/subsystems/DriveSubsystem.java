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
    
	private RobotDrive robotDrive;
	private final CANTalon frontLeftMotor = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
	private final CANTalon rearLeftMotor = new CANTalon(RobotMap.REAR_LEFT_DRIVE);
	private final CANTalon frontRightMotor = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
	private final CANTalon rearRightMotor = new CANTalon(RobotMap.REAR_RIGHT_DRIVE);
	
	private final Solenoid shiftSolenoid = new Solenoid(RobotMap.SHIFT_SOLENOID);
	
	public DriveSubsystem() {
		frontLeftMotor.setInverted(true);
		rearLeftMotor.setInverted(true);
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
    
    public void stopMe() {
    	robotDrive.tankDrive(0, 0);
    }
    
    public void shift() {
    	shiftSolenoid.set(!shiftSolenoid.get());
    }
    
    public boolean getShiftSolenoidState() {
    	return shiftSolenoid.get();
    }
    
    public void setShiftSolenoid(boolean on) {
    	shiftSolenoid.set(on);
    }
}

