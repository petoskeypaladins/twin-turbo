package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.HoodAngleCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	private CANTalon hoodAngleMotor = new CANTalon(RobotMap.HOOD_ANGLE_MOTOR);
	private CANTalon shootMotor = new CANTalon(RobotMap.SHOOT_MOTOR);
	
	private AnalogInput hoodAnglePotentiometer = new AnalogInput(RobotMap.HOOD_ANGLE_POTENTIOMETER);
	private Solenoid ballIndexSolenoid = new Solenoid(RobotMap.BALL_INDEX_PISTON);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new HoodAngleCommand());
    }
    
    
    public ShooterSubsystem() {
    	shootMotor.setInverted(true);
    	shootMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	shootMotor.reverseSensor(false);
    	shootMotor.configNominalOutputVoltage(+0.0f, -0.0f);
    	shootMotor.configPeakOutputVoltage(+12.0f, -12.0f);
    	shootMotor.setProfile(0);
    	shootMotor.setF(.03725);
    	shootMotor.setP(0);
    	shootMotor.setI(0);
    	shootMotor.setD(0);
    }
    
    public void setHoodAngle(double degrees) {
    	
    }
    
    public void setHoodAngleSpeed(double speed) {
    	hoodAngleMotor.set(speed);
    	SmartDashboard.putNumber("Hood Potentiometer", hoodAnglePotentiometer.getValue());
    }
    
    public void toggleBallIndexSolenoid() {
    	ballIndexSolenoid.set(!ballIndexSolenoid.get());
    	SmartDashboard.putBoolean("Ball Index Solenoid", ballIndexSolenoid.get());
    }
    
    public void setSpeed(double speed) {
		double MAX_SHOOTER_RPM = 4200;
		double rpm = MAX_SHOOTER_RPM * speed;
		double motorOutput = shootMotor.getOutputVoltage() / shootMotor.getBusVoltage();
		
		SmartDashboard.putNumber("Shooter Output", motorOutput);
		SmartDashboard.putNumber("Shooter Speed", shootMotor.getSpeed());
		System.out.println("Shooter Output: " + motorOutput);
		System.out.println("Shooter Speed: " + shootMotor.getSpeed());
		
		shootMotor.changeControlMode(TalonControlMode.Speed);
		shootMotor.set(rpm);
		
		SmartDashboard.putNumber("Shooter Error", shootMotor.getClosedLoopError());
		System.out.println("Shooter Target Speed: " + rpm);
    }
    
    public void setPower(double speed) {
    		shootMotor.changeControlMode(TalonControlMode.PercentVbus);
    		shootMotor.set(speed);
    		SmartDashboard.putNumber("ShooterSpeed", speed);
    }

}

