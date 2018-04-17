/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2609.robot;

import org.usfirst.frc.team2609.robot.commands.LaunchMP;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollect;
import org.usfirst.frc.team2609.robot.commands.automation.CubeGrab;
import org.usfirst.frc.team2609.robot.commands.automation.ForceShoot;
import org.usfirst.frc.team2609.robot.commands.automation.LayEgg;
import org.usfirst.frc.team2609.robot.commands.automation.SwitchScore;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.CubePincherSetState;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStopAuto;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.slider.SliderHome;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	

	// button.whenReleased(new ExampleCommand());
	
	public static Joystick driverStick;
	public static JoystickButton driverButton1;
	public static JoystickButton driverButton2;
	public static JoystickButton driverButton3;
	public static JoystickButton driverButton4;
	public static JoystickButton driverButton5;
	public static JoystickButton driverButton6;
	public static JoystickButton driverButton7;
	public static JoystickButton driverButton8;
	public static JoystickButton driverButton9;
	public static JoystickButton driverButton10;
	
	public static Joystick operatorStick;
	public static JoystickButton operatorButton1;
	public static JoystickButton operatorButton2;
	public static JoystickButton operatorButton3;
	public static JoystickButton operatorButton4;
	public static JoystickButton operatorButton5;
	public static JoystickButton operatorButton6;
	public static JoystickButton operatorButton7;
	public static JoystickButton operatorButton8;
	public static JoystickButton operatorButton9;
	public static JoystickButton operatorButton10;
	
	public OI(){
		driverStick = new Joystick(0);
		operatorStick = new Joystick(1);
		
		//driver inputs
		driverButton1 = new JoystickButton(driverStick, 1);
		driverButton1.whenPressed(new IntakeActivatorSetState(IntakeActivatorState.OUT)); //Open arm
		driverButton1.whenPressed(new CubePincherSetState(IntakeActivatorState.OUT));	//pincher out
		

		driverButton2 = new JoystickButton(driverStick, 2);
		driverButton2.whenPressed(new IntakeActivatorSetState(IntakeActivatorState.IN)); //Close arm
		driverButton2.whenPressed(new CubePincherSetState(IntakeActivatorState.OUT));	//pincher out
		
		driverButton3 = new JoystickButton(driverStick, 3);	//close arm more/cube pinch
		driverButton3.whenPressed(new IntakeActivatorSetState(IntakeActivatorState.IN)); //Close arm
		driverButton3.whenPressed(new CubePincherSetState(IntakeActivatorState.IN));	//pincher in
		driverButton3.whenPressed(new SliderPosition(500));
		driverButton3.whenPressed(new VaultBoyPower(0));
		driverButton3.whenPressed(new IntakeRollerPowerNoCurrent(0));
		driverButton3.whenPressed(new ShooterRoller(0));
		driverButton3.whenPressed(new ShooterActivatorSetState(ShooterActivatorState.UP));

		driverButton4 = new JoystickButton(driverStick, 4);
		//Button 4 activates cheezydrive quickturn in DriveTeleop

		driverButton5 = new JoystickButton(driverStick, 5);
		driverButton5.whenPressed(new CubeGrab());
		
		driverButton6 = new JoystickButton(driverStick, 6); // Cube Collect
//		driverButton6.whenPressed(new IntakeActivatorSetState(IntakeActivatorState.OUT));
		driverButton6.whenPressed(new CubeCollect());
		
		driverButton7 = new JoystickButton(driverStick, 7);//switch score fast
		driverButton7.whenPressed(new SwitchScore(0.8)); 

		driverButton8 = new JoystickButton(driverStick, 8);	//Switch score normal/slow
		driverButton8.whenPressed(new SwitchScore(0.6)); 
		
		driverButton9 = new JoystickButton(driverStick, 9);
		driverButton9.whenPressed(new IntakeRollerPower(0.45,5));
		
		driverButton10 = new JoystickButton(driverStick, 10);	//Reset
		driverButton10.whenPressed(new SliderPosition(500));
		driverButton10.whenPressed(new VaultBoyPower(0));
		driverButton10.whenPressed(new IntakeRollerPowerNoCurrent(0));
		driverButton10.whenPressed(new ShooterRoller(0));
		driverButton10.whenPressed(new ShooterActivatorSetState(ShooterActivatorState.UP));
		driverButton10.whenPressed(new CubePincherSetState(IntakeActivatorState.OUT));	//pincher out
		
		
		
		//operator inputs
		
		operatorButton2 = new JoystickButton(operatorStick, 2);
		operatorButton2.whenPressed(new ForceShoot());
//		operatorButton2.whileHeld(new VaultBoyPower(0.75));
		operatorButton3 = new JoystickButton(operatorStick, 3);
//		operatorButton3.whileHeld(new ShooterRoller(1));
//		operatorButton3.whenReleased(new ShooterRoller(0));
		operatorButton4 = new JoystickButton(operatorStick, 4);
//		operatorButton4.whenReleased(new ShooterRoller(0));
//		operatorButton4.whileHeld(new IntakeActivatorSetState(IntakeActivatorState.OUT));
//		operatorButton4.whenPressed(new CubePincherSetState(IntakeActivatorState.OUT));	//pincher out
//		operatorButton4.whileHeld(new ShooterActivatorSetState(ShooterActivatorState.DOWN));
//		operatorButton4.whileHeld(new VaultBoyPower(-0.75));
		
		operatorButton5 = new JoystickButton(operatorStick, 5);	//vault score
		operatorButton5.whileHeld(new VaultBoyPower(0.75));
		operatorButton5.whileHeld(new IntakeRollerPowerNoCurrent(0.45));
		operatorButton5.whileHeld(new ShooterRoller(-0.3));
		operatorButton5.whenReleased(new VaultBoyPower(0));
		operatorButton5.whenReleased(new IntakeRollerPowerNoCurrent(0));
		operatorButton5.whenReleased(new ShooterRoller(0));
		
		operatorButton6 = new JoystickButton(operatorStick, 6);	//cube out front
		operatorButton6.whileHeld(new VaultBoyPower(-0.5));
		operatorButton6.whileHeld(new IntakeRollerPowerNoCurrent(-0.4));
		operatorButton6.whileHeld(new ShooterRoller(-0.3));
		operatorButton6.whenReleased(new VaultBoyPower(0));
		operatorButton6.whenReleased(new IntakeRollerPowerNoCurrent(0));
		operatorButton6.whenReleased(new ShooterRoller(0));
		
//		operatorButton6.whenPressed(new CubeGrab());
		operatorButton7 = new JoystickButton(operatorStick, 7);
//		operatorButton7.whenPressed(new SliderPosition(500));
		operatorButton8 = new JoystickButton(operatorStick, 8);
//		operatorButton8.whenPressed(new SwitchScore());

		operatorButton10 = new JoystickButton(operatorStick, 10);
		operatorButton10.whenPressed(new SliderHome());
	}
}
