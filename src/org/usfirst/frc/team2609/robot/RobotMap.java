/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2609.robot;

import org.usfirst.frc.team2609.BeaverTalonSRX;
import org.usfirst.frc.team2609.MP.MPConstants;
import org.usfirst.frc.team2609.MP.MPRoutine;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import jaci.pathfinder.modifiers.TankModifier;

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
	public static BeaverTalonSRX driveLeft1;
	public static BeaverTalonSRX driveLeft2;
	public static BeaverTalonSRX driveRight1;
	public static BeaverTalonSRX driveRight2;
	              
	public static BeaverTalonSRX driveLeftEncoder;
	              
	//vault boy   
	public static BeaverTalonSRX vaultBoyLeft;
	public static BeaverTalonSRX vaultBoyRight;
	public static BeaverTalonSRX slider;
	public static BeaverTalonSRX intakeRollerLeft;
	public static BeaverTalonSRX intakeRollerRight;
	
	//misc
	public static AHRS ahrs;
	public static PowerDistributionPanel pdp;
	public static Compressor compressor;
	public static DigitalInput cubeSensor;
	
	//pneumatics
    public static DoubleSolenoid shooterActivator;
    public static DoubleSolenoid intakeActivator;
    
    
    //MP
    public static TankModifier[] plannedPath;
    public static boolean[] isGenerated;
    public static boolean[] isDoneMP;
    public static MPRoutine mpRoutine;
	
	public static void init(){
		
		//drivetrain
		driveLeft1 = new BeaverTalonSRX(1);
		driveLeft2 = new BeaverTalonSRX(2);
		driveRight1 = new BeaverTalonSRX(11);
		driveRight2 = new BeaverTalonSRX(10);

		driveLeft1.setInverted(true);
		driveLeft2.setInverted(true);
		driveRight1.setInverted(false);
		driveRight2.setInverted(false);

		driveLeft1.setEncPerRevsQuad(MPConstants.leftEncPerFeet); // to feet
		driveRight1.setEncPerRevsQuad(MPConstants.rightEncPerFeet);
		
		driveLeft1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		

		driveLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, 0);
		driveLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 0);
		driveLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 0);
		driveRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 0);
		driveRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, 0);
		driveRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 0);
		
		//vault boy
		vaultBoyLeft = new BeaverTalonSRX(3);
		vaultBoyRight = new BeaverTalonSRX(9);
		slider = new BeaverTalonSRX(6);
		intakeRollerLeft = new BeaverTalonSRX(4);
		intakeRollerRight = new BeaverTalonSRX(8);
		cubeSensor = new DigitalInput(0);
		
		intakeRollerLeft.setNeutralMode(NeutralMode.Brake);
		intakeRollerRight.setNeutralMode(NeutralMode.Brake);
		
		slider.setInverted(true);
		slider.setSensorPhase(true);
		slider.configOpenloopRamp(0.1, 10);
		
		//pneumatics
		shooterActivator = new DoubleSolenoid(0,1,2);
		intakeActivator = new DoubleSolenoid(0,0,3);
		
		//misc
		pdp = new PowerDistributionPanel(12);
		compressor = new Compressor(0);
		
		//gyro error handling
		try{
			ahrs = new AHRS(SPI.Port.kMXP);
		}catch (RuntimeException ex){
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}
		
		
	}
	
}
