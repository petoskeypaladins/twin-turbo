package org.usfirst.frc.team3618.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int FRONT_LEFT_DRIVE = 4;
	public static final int REAR_LEFT_DRIVE = 9;
	public static final int FRONT_RIGHT_DRIVE = 1;
	public static final int REAR_RIGHT_DRIVE = 5;
	
	public static final int HOOD_ANGLE_MOTOR = 3;
	public static final int SHOOT_MOTOR = 2;
	public static final int BALL_INTAKE_MOTOR = 10;
	public static final int AGITATOR_MOTOR = 11;
	public static final int HOOD_ANGLE_POTENTIOMETER = 0;

	public static final int GEAR_CLAMP_PISTON = 2;
	public static final int GEAR_LIFT_PISTON = 0;
	public static final int SHIFT_SOLENOID = 1;
	public static final int BALL_INDEX_PISTON = 3;
//	public static final int CLIMB_MOTOR = 0;
}
