package org.usfirst.frc.team3618.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3618.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearLiftSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid clampPiston = new Solenoid(RobotMap.GEAR_CLAMP_PISTON);
	private Solenoid liftPiston = new Solenoid(RobotMap.GEAR_LIFT_PISTON);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void toggleLift() {
    	liftPiston.set(!liftPiston.get()); //true is up
        SmartDashboard.putBoolean("Lift Position", liftPiston.get());
    }
    
    public void toggleClamp() {
    	clampPiston.set(!clampPiston.get());
        SmartDashboard.putBoolean("Clamp Position", clampPiston.get());
    }

    public void setClampPiston(boolean on) {
        clampPiston.set(on);
        SmartDashboard.putBoolean("Clamp Position", clampPiston.get());
    }

    public boolean getClampState() {
        return clampPiston.get();
    }
}

