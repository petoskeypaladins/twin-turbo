package org.usfirst.frc.team3618.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		private boolean isCompetitionBot = true;
		public static final int FRONT_LEFT_DRIVE = 2;
		public static final int REAR_LEFT_DRIVE = 3;
		public static final int FRONT_RIGHT_DRIVE = 4;
		public static final int REAR_RIGHT_DRIVE = 5;
		
		public static final int HOOD_ANGLE_MOTOR = 8;
		public static final int SHOOT_MOTOR = 10;
		public static final int BALL_INTAKE_MOTOR = 9;
		public static final int AGITATOR_MOTOR = 6;
		public static final int CLIMB_MOTOR = 7;
		
		public static final int HOOD_ANGLE_POTENTIOMETER = 3;
		public static final int BALL_INDEX_PHOTOSENSOR = 0;
		
		public static final int GEAR_CLAMP_PISTON = 2;
		public static final int GEAR_LIFT_PISTON = 0;
		public static final int SHIFT_SOLENOID = 6;
		public static final int BALL_INDEX_PISTON = 4;

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
