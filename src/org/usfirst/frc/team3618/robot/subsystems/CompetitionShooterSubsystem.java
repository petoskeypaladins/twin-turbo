package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.HoodAngleCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CompetitionShooterSubsystem extends ShooterSubsystem
		implements IndexableShooterSubsystem {

	private DoubleSolenoid ballIndexSolenoid = new DoubleSolenoid(RobotMap.BALL_INDEX_PISTON, RobotMap.BALL_INDEX_PISTON + 1);

    public CompetitionShooterSubsystem() {
    	shootMotor.setInverted(true);
    	shootMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	shootMotor.reverseSensor(false);
    	shootMotor.configNominalOutputVoltage(+0.0f, -0.0f);
    	shootMotor.configPeakOutputVoltage(+12.0f, -12.0f);
    	shootMotor.setProfile(0);
    	shootMotor.setF(.3075);
    	shootMotor.setP(0);
    	shootMotor.setI(0);
    	shootMotor.setD(0);
		shootMotor.changeControlMode(TalonControlMode.Speed);
    }

    public void toggleBallIndexSolenoid() {
    	System.out.println("runnint ball index function");
    	boolean on = ballIndexSolenoid.get() == DoubleSolenoid.Value.kForward;
    	System.out.println("initial: " + ballIndexSolenoid.get());
    	DoubleSolenoid.Value ballIndexValue = !on ?
    			DoubleSolenoid.Value.kForward :
    			DoubleSolenoid.Value.kReverse;
    	System.out.println("new: " + ballIndexValue.toString());
    	ballIndexSolenoid.set(ballIndexValue);
    	System.out.println("final: " + ballIndexSolenoid.get());
    	SmartDashboard.putString("Ball Index Solenoid", ballIndexSolenoid.get().toString());
    }

	@Override
	public void setBallIndexSolenoid(boolean forward) {
		DoubleSolenoid.Value solenoidValue = forward ?
				DoubleSolenoid.Value.kForward :
				DoubleSolenoid.Value.kReverse; 
		ballIndexSolenoid.set(solenoidValue);
	}

	@Override
	public boolean getBallIndexSolenoid() {
		// TODO Auto-generated method stub
		boolean forward = ballIndexSolenoid.get() == DoubleSolenoid.Value.kForward;
		return forward;
	}
}