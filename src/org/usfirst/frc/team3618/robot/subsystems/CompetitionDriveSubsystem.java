package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.DriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CompetitionDriveSubsystem extends DriveSubsystem 
		implements ShiftingDriveSubsystem {
    
	private final DoubleSolenoid shiftSolenoid = new DoubleSolenoid(RobotMap.SHIFT_SOLENOID, RobotMap.SHIFT_SOLENOID + 1);
	
	public CompetitionDriveSubsystem() {
		frontLeftMotor.setInverted(true);
		rearLeftMotor.setInverted(true);
	}

    public void shift() {
    	boolean on = shiftSolenoid.get() == DoubleSolenoid.Value.kForward;
    	DoubleSolenoid.Value shiftValue = !on ?
    			DoubleSolenoid.Value.kForward :
    			DoubleSolenoid.Value.kReverse;
    	shiftSolenoid.set(shiftValue);
    	SmartDashboard.putString("Shift Value", shiftSolenoid.get().toString());
    }
    
    public boolean getShiftSolenoidState() {
    	boolean on = shiftSolenoid.get() == DoubleSolenoid.Value.kForward;
    	return on;
    }
    
    public void setShiftSolenoid(boolean on) {
    	DoubleSolenoid.Value shiftValue = on ?
    			DoubleSolenoid.Value.kForward :
    			DoubleSolenoid.Value.kReverse;
    	shiftSolenoid.set(shiftValue);
    }
}

