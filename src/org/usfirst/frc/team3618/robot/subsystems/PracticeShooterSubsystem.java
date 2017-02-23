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
public class PracticeShooterSubsystem extends ShooterSubsystem 
		implements IndexableShooterSubsystem {

	private Solenoid ballIndexSolenoid = new Solenoid(RobotMap.BALL_INDEX_PISTON);

    public PracticeShooterSubsystem() {
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
    
    public void toggleBallIndexSolenoid() {
    	ballIndexSolenoid.set(!ballIndexSolenoid.get());
    	SmartDashboard.putBoolean("Ball Index Solenoid", ballIndexSolenoid.get());
    }

	@Override
	public void setBallIndexSolenoid(boolean forward) {
		ballIndexSolenoid.set(forward);
    	SmartDashboard.putBoolean("Ball Index Solenoid", ballIndexSolenoid.get());
	}

	@Override
	public boolean getBallIndexSolenoid() {
		// TODO Auto-generated method stub
		return ballIndexSolenoid.get();
	}
}

