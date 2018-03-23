/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2609.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;
import enums.ShooterActivatorState;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2609.BeaverTalonSRX;
import org.usfirst.frc.team2609.MP.AutoRoutine;
import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.Logger;
import org.usfirst.frc.team2609.MP.Looper;
import org.usfirst.frc.team2609.MP.MPConstants;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.MP.PathGenerator;
import org.usfirst.frc.team2609.robot.commands.SetMPRoutine;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollect;
import org.usfirst.frc.team2609.robot.commands.auton.FallbackCheck;
import org.usfirst.frc.team2609.robot.commands.auton.LeftSwitchVaultMPRoutine;
import org.usfirst.frc.team2609.robot.commands.auton.NoDriveRoutine;
import org.usfirst.frc.team2609.robot.commands.auton.RightSwitchVaultMPRoutine;
import org.usfirst.frc.team2609.robot.commands.auton.SwitchSwitchRoutine;
import org.usfirst.frc.team2609.robot.commands.auton.SwitchVaultMiddle;
import org.usfirst.frc.team2609.robot.commands.auton.SwitchVaultRoutine;
import org.usfirst.frc.team2609.robot.commands.auton.TestOnton;
import org.usfirst.frc.team2609.robot.commands.auton.fallbacks.FallbackSwitchVaultRoutine;
import org.usfirst.frc.team2609.robot.commands.drive.DriveGyroReset;
import org.usfirst.frc.team2609.robot.commands.drive.DriveStraightTrapezoid;
import org.usfirst.frc.team2609.robot.commands.drive.DriveTeleop;
import org.usfirst.frc.team2609.robot.commands.slider.SliderHome;
import org.usfirst.frc.team2609.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2609.robot.subsystems.Intake;
import org.usfirst.frc.team2609.robot.subsystems.Shooter;
import org.usfirst.frc.team2609.robot.subsystems.Slider;
import org.usfirst.frc.team2609.robot.subsystems.VaultBoy;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	// subsystem initialization
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final VaultBoy vaultBoy = new VaultBoy();
	public static final Slider slider = new Slider();
	public static final Intake intake = new Intake();
	public static final Shooter shooter= new Shooter();
	public static FallbackCheck fallback;
	
	//mp subsystems
	public static final Logger logger = Logger.getInstance();
	public static final PathGenerator pathGenerator = new PathGenerator();
	private Looper enabledLooper;

	public static OI m_oi;
	private DriverStation m_ds = DriverStation.getInstance();
	private AutoRoutine lastAuto;
	
	public static final double inchesToTicks = 217.29954896813443176978263159127;		//counts/inch
	public static final double ticksToInches = 0.00460194236365692368915426276848;		//inches/count
	public static Map<String, AutoRoutine> autoMap = new HashMap<String, AutoRoutine>();
	public static Map<String, AutoRoutine> fallbackMap = new HashMap<String, AutoRoutine>();
	
	public static boolean isDriveTrainMPActive;
	
	public static boolean driveSensors, sliderSensor;
	
	String m_autonomousString;
	Command m_autonomousCommand;
	SendableChooser<String> m_chooser = new SendableChooser<>();

	int count = 0; // to keep track of loop count
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); // imports all of RobotMap, required to not crash on
							// start up

//		CameraServer.getInstance().startAutomaticCapture();
		enabledLooper = new Looper();
		
		// DEFAULT!! MAKE SURE SPELLING MATCHES .addDefault
		autoMap.put("SwitchVaultRoutine", new SwitchVaultRoutine()); 
		autoMap.put("SwitchSwitchRoutine", new SwitchSwitchRoutine()); 
		autoMap.put("NoDriveRoutine", new NoDriveRoutine()); 
		fallbackMap.put("SwitchVaultRoutine", new FallbackSwitchVaultRoutine());
		fallbackMap.put("SwitchSwitchRoutine", new SwitchSwitchRoutine());
		fallbackMap.put("NoDriveRoutine", new NoDriveRoutine());
		
		
		
		fallback = new FallbackCheck();
		m_oi = new OI();
		m_chooser.addDefault("Switch2Vault MP", "SwitchVaultRoutine"); // make sure this is in the map
		m_chooser.addObject("Switch2Switch MP", "SwitchSwitchRoutine"); // make sure this is in the map
		m_chooser.addObject("Nodrive", "NoDriveRoutine"); // make sure this is in the map
		// loop through map and .addObject here
		
		
		SmartDashboard.putData("Auto mode", m_chooser);

		MPConstants.sdPut();
		drivetrain.initSd();
		slider.initSd();
    	
    	SmartDashboard.putNumber("Auto delay", 0);
    	
    	try{
    		enabledLooper.register(drivetrain.getLooper());
    		enabledLooper.register(slider.getLooper());
    	} catch(Throwable t){
    		System.out.println(t.getMessage());
    		System.out.println(t.getStackTrace());
    	}
    	driveSensors = drivetrain.initialSensorCheck();
    	sliderSensor = slider.initialSensorCheck();
    	
    	RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(RobotMap.gameData.length() > 0){
    		if(RobotMap.gameData.charAt(0)=='L'){
    			RobotMap.activeSide = AutoSide.LEFT;
    		}else{
    			RobotMap.activeSide = AutoSide.RIGHT;
    		}
    	}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		drivetrain.changeBreakMode(false);
		enabledLooper.stop();
		logger.close();
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		drivetrain.outputSd();
		intake.outputSd();
		slider.outputSd();
		vaultBoy.outputSd();
		shooter.outputSd();
		MPConstants.sdGet();
		if(lastAuto != fallback.getCheckedAutoCommand(m_chooser.getSelected())){
			fallback.getCheckedAutoCommand(m_chooser.getSelected()).forceSetMP();
			RobotMap.mpRoutineL.setPathsWithOffset(RobotMap.alliance);
			RobotMap.mpRoutineR.setPathsWithOffset(RobotMap.alliance);
			pathGenerator.generateAll();	
			DriverStation.reportWarning("NEW CONTROL DATA!", false);
			lastAuto = fallback.getCheckedAutoCommand(m_chooser.getSelected());
			
		}
		if(RobotMap.alliance != m_ds.getAlliance()){
			RobotMap.alliance = m_ds.getAlliance();
			DriverStation.reportWarning("NEW ALLIANCE DATA!", false);
		}
		if(count++ >= 20) { //update every 20th loop
			intake.cubeDisplay();
			count = 0; // reset count
		}

		pathGenerator.generateAll();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = fallback.getCheckedAutoCommand(m_chooser.getSelected());
//		m_autonomousCommand = m_chooser.getSelected();
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetGyro();
		Robot.intake.intakeRollerBrakeMode(true);
		enabledLooper.start();
		logger.openFile();
		RobotMap.driveLeft1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveLeft2.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight2.setNeutralMode(NeutralMode.Brake);
		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(RobotMap.gameData.length() > 0){
    		if(RobotMap.gameData.charAt(0)=='L'){
    			RobotMap.activeSide = AutoSide.LEFT;
    		}else{
    			RobotMap.activeSide = AutoSide.RIGHT;
    		}
    	}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		
		System.out.println(RobotMap.activeSide.toString());
		drivetrain.outputSd();
		intake.outputSd();
		slider.outputSd();
		vaultBoy.outputSd();
		shooter.outputSd();
		if(count++ >= 20) { //update every 20th loop
			intake.cubeDisplay();
			count = 0; // reset count
		}
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetGyro();
		Robot.intake.intakeRollerBrakeMode(true);
		
		logger.openFileTele();
		RobotMap.driveLeft1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveLeft2.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight2.setNeutralMode(NeutralMode.Brake);
		shooter.setShooterActivatorState(ShooterActivatorState.UP);
		new DriveTeleop().start();
		if(!RobotMap.isHomed){
			new SliderHome().start();
		}
		enabledLooper.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		drivetrain.outputSd();
		intake.outputSd();
		slider.outputSd();
		vaultBoy.outputSd();
		shooter.outputSd();

    	SmartDashboard.putBoolean("CubeDetect: ", RobotMap.cubeSensor.get());
		
//    	RobotMap.fishing.set(OI.operatorStick.getRawAxis(1));
		
		if(count++ >= 20) { //update every 20th loop
			intake.cubeDisplay();
			count = 0; // reset count
		}
    	
		if (OI.operatorButton2.get()){
			RobotMap.slider.set(ControlMode.PercentOutput, OI.operatorStick.getRawAxis(3)*0.2);
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
