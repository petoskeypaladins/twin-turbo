package org.usfirst.frc.team3618.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3618.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CompetitionGearLiftSubsystem extends Subsystem 
	implements GearLiftSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final DoubleSolenoid clampPiston = new DoubleSolenoid(RobotMap.GEAR_CLAMP_PISTON, RobotMap.GEAR_CLAMP_PISTON + 1);
	private final DoubleSolenoid liftPiston = new DoubleSolenoid(RobotMap.GEAR_LIFT_PISTON, RobotMap.GEAR_LIFT_PISTON + 1);
	
	public CompetitionGearLiftSubsystem() {
		System.out.println("competition initializing gear lift");
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void toggleLift() {
    	boolean forward = liftPiston.get() == DoubleSolenoid.Value.kForward;
    	DoubleSolenoid.Value liftValue = !forward ?
    			DoubleSolenoid.Value.kForward :
    			DoubleSolenoid.Value.kReverse;
    	liftPiston.set(liftValue);
        SmartDashboard.putString("Lift Position", liftPiston.get().toString());
    }
    
    public void toggleClamp() {
    	boolean forward = clampPiston.get() == DoubleSolenoid.Value.kForward;
    	DoubleSolenoid.Value clampValue = !forward ?
    			DoubleSolenoid.Value.kForward :
    			DoubleSolenoid.Value.kReverse;
    	clampPiston.set(clampValue);
        SmartDashboard.putString("Clamp Position", clampPiston.get().toString());
    }

    public void setClampPiston(boolean on) {
    	DoubleSolenoid.Value clampValue = on ?
    			DoubleSolenoid.Value.kForward :
				DoubleSolenoid.Value.kReverse;
        clampPiston.set(clampValue);
        SmartDashboard.putString("Clamp Position", clampPiston.get().toString());
    }

    public boolean getClampState() {
    	boolean clampState = clampPiston.get() == DoubleSolenoid.Value.kForward;
        return clampState;
    }
}

