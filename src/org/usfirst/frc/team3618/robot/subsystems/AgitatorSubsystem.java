package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.AgitatorCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AgitatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon agitatorMotor = new CANTalon(RobotMap.AGITATOR_MOTOR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public AgitatorSubsystem() {
    	agitatorMotor.setInverted(true);
    }
    
    public void setSpeed(double speed) {
    	agitatorMotor.set(speed);
    	SmartDashboard.putNumber("Agitator Speed", speed);
    }
}

