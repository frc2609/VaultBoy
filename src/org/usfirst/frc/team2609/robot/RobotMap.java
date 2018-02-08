/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2609.robot;

import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	

	//drivetrain
	public static TalonSRX driveLeft1;
	public static TalonSRX driveLeft2;
	public static TalonSRX driveRight1;
	public static TalonSRX driveRight2;
	
	//vault boy
	public static TalonSRX vaultBoyLeft;
	public static TalonSRX vaultBoyRight;
	public static TalonSRX slider;
	public static TalonSRX intakeRollerLeft;
	public static TalonSRX intakeRollerRight;
	
	//misc
	public static AHRS ahrs;
	
	public static void init(){
		
		//drivetrain
		driveLeft1 = new TalonSRX(1);
		driveLeft2 = new TalonSRX(2);
		driveRight1 = new TalonSRX(10);
		driveRight2 = new TalonSRX(11);

		driveLeft1.setInverted(false);
		driveLeft1.setSensorPhase(true);
		driveLeft2.setInverted(false);
		driveLeft2.setSensorPhase(true);
		driveRight1.setInverted(true);
		driveRight1.setSensorPhase(true);
		driveRight2.setInverted(true);
		driveRight2.setSensorPhase(true);
		
		//vault boy
		vaultBoyLeft = new TalonSRX(3);
		vaultBoyRight = new TalonSRX(9);
		slider = new TalonSRX(6);
		intakeRollerLeft = new TalonSRX(5);
		intakeRollerRight = new TalonSRX(6);
		
		slider.setInverted(true);
		slider.setSensorPhase(false);
		slider.configOpenloopRamp(0.1, 10);
		
		//gyro error handling
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex){
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}
		
		
	}
	
}
