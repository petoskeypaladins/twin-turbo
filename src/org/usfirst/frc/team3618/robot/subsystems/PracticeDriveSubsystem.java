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
public class PracticeDriveSubsystem extends DriveSubsystem 
		implements ShiftingDriveSubsystem {
    
	private final Solenoid shiftSolenoid = new Solenoid(RobotMap.SHIFT_SOLENOID);
	
	public PracticeDriveSubsystem() {
		frontLeftMotor.setInverted(true);
		rearLeftMotor.setInverted(true);
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

