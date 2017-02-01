package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.DriveCommand;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	private RobotDrive robotDrive;
	private Talon frontLeftMotor = new Talon(RobotMap.FRONT_LEFT_MOTOR);
	private Talon rearLeftMotor = new Talon(RobotMap.REAR_LEFT_MOTOR);
	private Talon frontRightMotor = new Talon(RobotMap.FRONT_RIGHT_MOTOR);
	private Talon rearRightMotor = new Talon(RobotMap.REAR_RIGHT_MOTOR);

	public DriveSubsystem() {
		rearLeftMotor.setInverted(true);
		rearRightMotor.setInverted(true);
		robotDrive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		robotDrive.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void driveMecanum(double magnitude, double direction, double rotation) {
    	direction = -direction;
    	robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
		SmartDashboard.putNumber("Rotation", rotation);
		SmartDashboard.putNumber("Magnitude", magnitude);
		SmartDashboard.putNumber("Direction", direction);

    }
    
    public void driveTank(double leftValue, double rightValue) {
    	robotDrive.tankDrive(leftValue, rightValue);
    }
    
    public void stopMe() {
    	robotDrive.tankDrive(0, 0);
    }
}

