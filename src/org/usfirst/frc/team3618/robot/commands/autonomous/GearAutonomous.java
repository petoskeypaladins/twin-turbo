package org.usfirst.frc.team3618.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearAutonomous extends CommandGroup {
	private int lift;
	private final double FIRST_DISTANCE = 98.25 ;
	private final double[] DIRECTIONS = {45, 0, -45};

    public GearAutonomous(int lift) {
    	this.lift = lift;
    	addSequential(new DriveStraightCommand(FIRST_DISTANCE));
    	addSequential(new RotateCommand(DIRECTIONS[lift]));
    	addSequential(new PlaceGearCommand());
    }
}
