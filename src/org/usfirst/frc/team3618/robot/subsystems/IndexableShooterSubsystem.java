package org.usfirst.frc.team3618.robot.subsystems;

public interface IndexableShooterSubsystem {
    public void toggleBallIndexSolenoid();
    public void setBallIndexSolenoid(boolean forward);
    public boolean getBallIndexSolenoid();
}
