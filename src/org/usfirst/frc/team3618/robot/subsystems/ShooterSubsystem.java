package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.HoodAngleCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public abstract class ShooterSubsystem extends Subsystem {

	private final CANTalon hoodAngleMotor = new CANTalon(RobotMap.HOOD_ANGLE_MOTOR);
	protected final CANTalon shootMotor = new CANTalon(RobotMap.SHOOT_MOTOR);
	private final CANTalon secondaryShootMotor = new CANTalon(RobotMap.SECONDARY_SHOOT_MOTOR);
	
	private final double HOOD_MAX = 1141;
	private final double HOOD_MIN = 1392;
	
	private final AnalogInput hoodAnglePotentiometer = new AnalogInput(RobotMap.HOOD_ANGLE_POTENTIOMETER);
//	private final DigitalInput photoSensor = new DigitalInput(RobotMap.BALL_INDEX_PHOTOSENSOR);
	
	public ShooterSubsystem() {
		secondaryShootMotor.changeControlMode(TalonControlMode.Follower);
		secondaryShootMotor.set(shootMotor.getDeviceID());
		if (Robot.isCompetitionBot) {
			shootMotor.setInverted(true);
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new HoodAngleCommand());
    }
    
    
    public void setHoodAngle(double degrees) {
    	
    }
    
    public void setHoodAngleSpeed(double speed) {
    	if (Robot.isCompetitionBot) {
    		if (hoodAnglePotentiometer.getValue() < HOOD_MAX && speed < 0 ) {
    			speed = 1773;
    		} else if (hoodAnglePotentiometer.getValue() > HOOD_MIN && speed > 0) {
    			speed = 2040;
    		}
    	}
    	hoodAngleMotor.set(speed);
    	SmartDashboard.putNumber("Hood Potentiometer", hoodAnglePotentiometer.getValue());
    }
    
    
    public void printPosition() {
//    	System.out.println("status: " + shootMotor.isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute).toString());
//		System.out.println("position: " + shootMotor.getEncPosition());
    }
    
    public void setSpeed(double rpm) {
		double motorOutput = shootMotor.getOutputVoltage() / shootMotor.getBusVoltage();
		
		SmartDashboard.putNumber("Shooter Output", motorOutput);
		SmartDashboard.putNumber("Shooter Speed", shootMotor.getSpeed());
		SmartDashboard.putNumber("Shooter Output: ", motorOutput);
		SmartDashboard.putNumber("Shooter Speed: ", shootMotor.getSpeed());
		
		shootMotor.changeControlMode(TalonControlMode.Speed);
		shootMotor.set(rpm/10);
		
		SmartDashboard.putNumber("Shooter Error", shootMotor.getClosedLoopError());
		SmartDashboard.putNumber("Shooter Target Speed: ", rpm);
    }
    
    public double getSpeed() {
    	return shootMotor.getSpeed();
    }
    
    public double getPosition() {
    	return shootMotor.getPosition();
    }
    
    public void setPower(double speed) {
    	shootMotor.changeControlMode(TalonControlMode.PercentVbus);
    	shootMotor.set(speed);
    	SmartDashboard.putNumber("ShooterSpeed", speed);
    	SmartDashboard.putNumber("ShooterRpm", shootMotor.getSpeed());
    }
    
//    public boolean getPhotoSensor() {
//    	return photoSensor.get();
//    }

}

