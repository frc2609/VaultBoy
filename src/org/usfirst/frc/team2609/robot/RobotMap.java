/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2609.robot;

import org.usfirst.frc.team2609.BeaverDigitalInput;
import org.usfirst.frc.team2609.BeaverTalonSRX;
import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.MPConstants;
import org.usfirst.frc.team2609.MP.MPRoutine;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import enums.DriveActivatorState;
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
	public static boolean isHomed = false;
	//drivetrain
	public static BeaverTalonSRX driveLeft1;
	public static BeaverTalonSRX driveLeft2;
	public static BeaverTalonSRX driveRight1;
	public static BeaverTalonSRX driveRight2;
	              
	public static BeaverTalonSRX driveLeftEncoder;
	
	//drivetrain grouped
//	public static SpeedControllerGroup drivetrainLeft;
//	public static SpeedControllerGroup drivetrainRight;
//	
//	public static DifferentialDrive drivetrainFull;
	
	//vault boy   
	public static BeaverTalonSRX vaultBoyLeft;
	public static BeaverTalonSRX vaultBoyRight;
	public static BeaverTalonSRX slider;
	public static BeaverTalonSRX intakeRollerLeft;
	public static BeaverTalonSRX intakeRollerRight;
	public static BeaverTalonSRX shooterRight;
	public static BeaverTalonSRX shooterLeft;
	
	
	//misc
	public static AHRS ahrs;
	public static PowerDistributionPanel pdp;
	public static Compressor compressor;
	public static BeaverDigitalInput cubeSensor;
	
	public static BeaverDigitalInput cubeSensorArm;	//cube sensor on intake arm
	public static BeaverDigitalInput shooterSensor;
	
	//pneumatics
    public static DoubleSolenoid shooterActivator;
    public static DoubleSolenoid intakeActivator;
    public static DoubleSolenoid cubePincher;
    
    
    //MP
    public static TankModifier[] plannedPathL;
    public static TankModifier[] plannedPathR;
    public static boolean[] isGeneratedR;
    public static boolean[] isDoneMPR;
    public static boolean[] isGeneratedL;
    public static boolean[] isDoneMPL;
    public static MPRoutine mpRoutineL;
    public static MPRoutine mpRoutineR;
    public static AutoSide activeSide;
    public static DriveActivatorState teleopState;
    
    
    public static Alliance alliance = Alliance.Invalid;
    public static String gameData;
//    public static VideoSink server;

	public static void init(){
		

		try {
			UsbCamera cameraRear = CameraServer.getInstance().startAutomaticCapture(1);
			cameraRear.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 15);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating Rear camera:  " + ex.getMessage(), true);
        }
		try {
			UsbCamera cameraFront = CameraServer.getInstance().startAutomaticCapture(0);
			cameraFront.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 15);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating Front camera:  " + ex.getMessage(), true);
        }
		//drivetrain
		driveLeft1 = new BeaverTalonSRX(1);
		driveLeft2 = new BeaverTalonSRX(2);
		driveRight1 = new BeaverTalonSRX(11);
		driveRight2 = new BeaverTalonSRX(10);

		shooterLeft = new BeaverTalonSRX(5);
		shooterRight = new BeaverTalonSRX(7);

		driveLeft1.setInverted(true);
		driveLeft2.setInverted(true);
		driveRight1.setInverted(false);
		driveRight2.setInverted(false);
		
		driveLeft1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		driveRight1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

		driveLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, 0);
		driveLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 0);
		driveLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 0);
		driveRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 0);
		driveRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, 0);
		driveRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 0);

		driveLeft1.setEncPerRevsQuad(MPConstants.leftEncPerFeet); // to feet
		driveRight1.setEncPerRevsQuad(MPConstants.rightEncPerFeet);
		
		//drivetrain grouped
//		drivetrainLeft = new SpeedControllerGroup(driveLeft1,driveLeft2);
//		drivetrainRight = new SpeedControllerGroup(driveRight1,driveRight2);
//		
//		drivetrainFull = new DifferentialDrive(drivetrainLeft,drivetrainRight);
		
		
		//vault boy
		vaultBoyLeft = new BeaverTalonSRX(3);
		vaultBoyRight = new BeaverTalonSRX(9);
		slider = new BeaverTalonSRX(6);
		intakeRollerLeft = new BeaverTalonSRX(4);
		intakeRollerRight = new BeaverTalonSRX(8);
		cubeSensor = new BeaverDigitalInput(9);
		
		cubeSensorArm = new BeaverDigitalInput(8); //Second cube sensor
		shooterSensor = new BeaverDigitalInput(7);
		
		
		intakeRollerLeft.setInverted(true);
		intakeRollerRight.setInverted(true);

		intakeRollerLeft.configOpenloopRamp(0.5, 0);
		intakeRollerRight.configOpenloopRamp(0.5, 0);
		
		slider.setInverted(false); // +ve is in and -ve is out
		slider.setSensorPhase(false);

		slider.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 10, 0);
		slider.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, 0);
		slider.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, 0);
		slider.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
		slider.configPeakCurrentDuration(0, 0);
		slider.configPeakCurrentLimit(0, 0);
		slider.configContinuousCurrentLimit(0, 0);
		slider.enableCurrentLimit(false);
		slider.configOpenloopRamp(0.1, 10);
		
		vaultBoyRight.setInverted(true);
		vaultBoyLeft.enableVoltageCompensation(true);
		vaultBoyLeft.configVoltageCompSaturation(12, 0);
		vaultBoyRight.enableVoltageCompensation(true);
		vaultBoyRight.configVoltageCompSaturation(12, 0);
		
		//pneumatics
		shooterActivator = new DoubleSolenoid(0,6,5);
		intakeActivator = new DoubleSolenoid(0,4,7);
		cubePincher = new DoubleSolenoid(0,0,1);
		
		//misc
		pdp = new PowerDistributionPanel(12);
		compressor = new Compressor(0);
		
		//gyro error handling
		try{
			ahrs = new AHRS(SPI.Port.kMXP);
		}catch (RuntimeException ex){
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}

		shooterLeft.configOpenloopRamp(0, 0);
		shooterRight.configOpenloopRamp(0, 0);

		intakeRollerRight.setInverted(false);
		intakeRollerLeft.setInverted(false);
//		server.setSource(cam1);
		
	}
	
}
