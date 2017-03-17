package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Spark ballIntakeMotor = new Spark(RobotMap.BALL_INTAKE_MOTOR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public BallIntakeSubsystem() {
//    	ballIntakeMotor.setInverted(true);
    }
    
    public void setSpeed(double speed) {
    	ballIntakeMotor.set(speed);
    }
    
    public void setDirection(int direction) {
    	setSpeed(-direction);
    }
    
}

