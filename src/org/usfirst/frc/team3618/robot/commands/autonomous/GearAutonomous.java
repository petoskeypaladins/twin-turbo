package org.usfirst.frc.team3618.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearAutonomous extends CommandGroup {
	private int lift;
	private final double FIRST_DISTANCE = 95;
	private final double STRAIGHT_DISTANCE = 30;
	private final double BACK_OFF_DISTANCE = -25;
	private final double[] DIRECTIONS = {60, 0, -60};

    public GearAutonomous(int lift) {
    	this.lift = lift;
    	if (lift != 1) {
    		addSequential(new DriveStraightCommand(FIRST_DISTANCE), 6);
    		addSequential(new RotateCommand(DIRECTIONS[this.lift]));
    		addSequential(new PlaceGearCommand());
    	} else {
    		addSequential(new DriveStraightCommand(STRAIGHT_DISTANCE), 3);
    		addSequential(new DriveStraightCommand(BACK_OFF_DISTANCE), 1);
    	}
    }
}
