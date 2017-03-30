package org.usfirst.frc.team3618.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		public static int FRONT_LEFT_DRIVE = 2;
		public static int REAR_LEFT_DRIVE = 3;
		public static int FRONT_RIGHT_DRIVE = 4;
		public static int REAR_RIGHT_DRIVE = 5;
		
		public static int HOOD_ANGLE_MOTOR = 8;
		public static int SHOOT_MOTOR = 10;
		public static int SECONDARY_SHOOT_MOTOR = 9;
		public static int BALL_INTAKE_MOTOR = 0;
		public static int AGITATOR_MOTOR = 6;
		public static int CLIMB_MOTOR = 7;
	
		public static int HOOD_ANGLE_POTENTIOMETER = 3;
		public static int BALL_INDEX_PHOTOSENSOR = 0;
		
		//COMPETITION
//		public static int GEAR_CLAMP_PISTON = 2;
//		public static int GEAR_LIFT_PISTON = 0;
//		public static int SHIFT_SOLENOID = 6;
//		public static int BALL_INDEX_PISTON = 4;
		//PRACTICE
		public static int GEAR_CLAMP_PISTON = 2;
		public static int GEAR_LIFT_PISTON = 0;
		public static int SHIFT_SOLENOID = 1;
		public static int BALL_INDEX_PISTON = 3;

		public RobotMap() {
//			if (isCompetitionBot) {
//				FRONT_LEFT_DRIVE = 2;
//				REAR_LEFT_DRIVE = 3;
//				FRONT_RIGHT_DRIVE = 4;
//				REAR_RIGHT_DRIVE = 5;
//				
//				HOOD_ANGLE_MOTOR = 8;
//				SHOOT_MOTOR = 10;
//				BALL_INTAKE_MOTOR = 6;
//				AGITATOR_MOTOR = 9;
//				CLIMB_MOTOR = 7;
//
//				HOOD_ANGLE_POTENTIOMETER = 0;
//
//				GEAR_CLAMP_PISTON = 2;
//				GEAR_LIFT_PISTON = 0;
//				SHIFT_SOLENOID = 6;
//				BALL_INDEX_PISTON = 4;
//			} else {
//				FRONT_LEFT_DRIVE = 4;
//				REAR_LEFT_DRIVE = 9;
//				FRONT_RIGHT_DRIVE = 1;
//				REAR_RIGHT_DRIVE = 5;
//				
//				HOOD_ANGLE_MOTOR = 3;
//				SHOOT_MOTOR = 2;
//				BALL_INTAKE_MOTOR = 10;
//				AGITATOR_MOTOR = 11;
//				CLIMB_MOTOR = 0;
//
//				HOOD_ANGLE_POTENTIOMETER = 0;
//
//				GEAR_CLAMP_PISTON = 2;
//				GEAR_LIFT_PISTON = 0;
//				SHIFT_SOLENOID = 1;
//				BALL_INDEX_PISTON = 3;
//			}
		}
}
