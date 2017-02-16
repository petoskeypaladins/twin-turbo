package org.usfirst.frc.team3618.robot;

import org.usfirst.frc.team3618.robot.commands.AgitatorCommand;
import org.usfirst.frc.team3618.robot.commands.BallIndexCommand;
import org.usfirst.frc.team3618.robot.commands.BallIntakeCommand;
import org.usfirst.frc.team3618.robot.commands.ClampGearCommand;
import org.usfirst.frc.team3618.robot.commands.EjectBallCommand;
import org.usfirst.frc.team3618.robot.commands.LiftGearCommand;
import org.usfirst.frc.team3618.robot.commands.ShiftCommand;
import org.usfirst.frc.team3618.robot.commands.SpinShooterCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final Joystick operatorJoystick = new Joystick(0);
	public static final XboxController driveController = new XboxController(1);
	
	private Button gearLiftButton = new JoystickButton(operatorJoystick, 11);
	private Button gearClampButton = new JoystickButton(operatorJoystick, 12);
	private Button spinShooterButton = new JoystickButton(operatorJoystick, 2);
	private Button ballIndexToggleButton = new JoystickButton(operatorJoystick, 1);
	private Button ballIntakeForwardButton = new JoystickButton(operatorJoystick, 3);
	private Button ballIntakeBackwardButton = new JoystickButton(operatorJoystick, 4);
	private Button agitatorForwardButton = new JoystickButton(operatorJoystick, 7);
	private Button agitatorBackwardButton = new JoystickButton(operatorJoystick, 8);
	private Button ejectBallButton = new JoystickButton(operatorJoystick, 5);
	private Button shiftButton = new JoystickButton(driveController, 6);

	
	public OI() {
		gearLiftButton.whenPressed(new LiftGearCommand());
		gearClampButton.whenPressed(new ClampGearCommand());
		shiftButton.whenPressed(new ShiftCommand());
		spinShooterButton.whileHeld(new SpinShooterCommand());
		ballIndexToggleButton.whenPressed(new BallIndexCommand());
		ballIntakeForwardButton.toggleWhenPressed(new BallIntakeCommand(BallIntakeCommand.FORWARD));
		ballIntakeBackwardButton.toggleWhenPressed(new BallIntakeCommand(BallIntakeCommand.BACKWARD));
		ejectBallButton.whileHeld(new EjectBallCommand());
		agitatorForwardButton.whileHeld(new AgitatorCommand(AgitatorCommand.FORWARD));
		agitatorBackwardButton.whileHeld(new AgitatorCommand(AgitatorCommand.BACKWARD));
	}
	
}

