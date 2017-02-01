package org.usfirst.frc.team3618.robot;


import org.usfirst.frc.team3618.robot.commands.ClampGearCommand;
import org.usfirst.frc.team3618.robot.commands.LiftGearCommand;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final XboxController driveController = new XboxController(1);
	
	private Button liftGearButton = new JoystickButton(driveController, 5);
	private Button clampGearButton = new JoystickButton(driveController, 6);
	
	public OI() {
		clampGearButton.whenPressed(new ClampGearCommand());
		liftGearButton.whenPressed(new LiftGearCommand());
	}
	
}

