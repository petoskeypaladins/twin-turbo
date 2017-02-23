package org.usfirst.frc.team3618.robot.subsystems;

public interface GearLiftSubsystem {
    public void toggleLift();
    public void toggleClamp();
    public void setClampPiston(boolean on);
    public boolean getClampState();}
