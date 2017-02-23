package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RopeClimbSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon ropeClimbMotor = new CANTalon(RobotMap.CLIMB_MOTOR);
	
	public RopeClimbSubsystem() {
		ropeClimbMotor.setInverted(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed) {
    	ropeClimbMotor.set(speed);
    }
}

