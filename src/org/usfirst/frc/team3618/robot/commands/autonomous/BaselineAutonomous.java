package org.usfirst.frc.team3618.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BaselineAutonomous extends CommandGroup {
	private final double DISTANCE = 110;

    public BaselineAutonomous() {
    	addSequential(new DriveStraightCommand(DISTANCE), 6);
    }
}
