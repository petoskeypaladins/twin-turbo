package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearLiftSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	private final Solenoid liftSolenoid = new Solenoid(RobotMap.GEAR_LIFT_PISTON);
	private final Solenoid clampSolenoid = new Solenoid(RobotMap.GEAR_CLAMP_PISTON);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void toggleClamp() {
    	clampSolenoid.set(!clampSolenoid.get());
    }
    
    public void toggleLift() {
    	liftSolenoid.set(!liftSolenoid.get());
    }
}

