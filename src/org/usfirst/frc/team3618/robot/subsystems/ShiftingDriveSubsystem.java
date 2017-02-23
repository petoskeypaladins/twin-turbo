package org.usfirst.frc.team3618.robot.subsystems;

public interface ShiftingDriveSubsystem {
    public void shift();   
    public boolean getShiftSolenoidState();   
    public void setShiftSolenoid(boolean on);
}
