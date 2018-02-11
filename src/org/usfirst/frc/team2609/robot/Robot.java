/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2609.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2609.robot.commands.auton.switchVaultMiddle;
import org.usfirst.frc.team2609.robot.commands.auton.testOnton;
import org.usfirst.frc.team2609.robot.commands.drive.driveStraightTrapezoid;
import org.usfirst.frc.team2609.robot.commands.drive.driveTeleop;
import org.usfirst.frc.team2609.robot.commands.slider.sliderResetPosition;
import org.usfirst.frc.team2609.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2609.robot.subsystems.IntakeActivator;
import org.usfirst.frc.team2609.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team2609.robot.subsystems.ShooterActivator;
import org.usfirst.frc.team2609.robot.subsystems.Slider;
import org.usfirst.frc.team2609.robot.subsystems.VaultBoy;

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
	public static final IntakeRoller intakeRoller = new IntakeRoller();
	public static final ShooterActivator shooterActivator = new ShooterActivator();
	public static final IntakeActivator intakeActivator = new IntakeActivator();

	public static OI m_oi;
	
	public static final double inchesToTicks = 217.29954896813443176978263159127;		//counts/inch
	public static final double ticksToInches = 0.00460194236365692368915426276848;		//inches/count

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init(); // imports all of RobotMap, required to not crash on
							// start up

		m_oi = new OI();
		m_chooser.addDefault("testOnton", new testOnton());
		m_chooser.addObject("switchVaultMiddle", new switchVaultMiddle());
		m_chooser.addObject("testOnton", new testOnton());
		m_chooser.addObject("trapezoid", new driveStraightTrapezoid(10,70,80,0.2,0.5,0.1,0));
		m_chooser.addObject("slider reset", new sliderResetPosition());
		SmartDashboard.putData("Auto mode", m_chooser);

		//SmartDashboard Values initialization
		//Left drive PID
		SmartDashboard.putNumber("DriveLeft P: ", 0.0002);
    	SmartDashboard.putNumber("DriveLeft I: ", 0.000);
    	SmartDashboard.putNumber("DriveLeft D: ", 0.0);
    	SmartDashboard.putNumber("DriveLeft Max: ", 0.8);
    	SmartDashboard.putNumber("DriveLeft Eps: ", 1.0);
    	SmartDashboard.putNumber("DriveLeft DR: ", 10);
    	SmartDashboard.putNumber("DriveLeft DC: ", 5);
    	//Right drive PID
		SmartDashboard.putNumber("DriveRight P: ", 0.0002);
    	SmartDashboard.putNumber("DriveRight I: ", 0.000);
    	SmartDashboard.putNumber("DriveRight D: ", 0.0);
    	SmartDashboard.putNumber("DriveRight Max: ", 0.8);
    	SmartDashboard.putNumber("DriveRight Eps: ", 1.0);
    	SmartDashboard.putNumber("DriveRight DR: ", 10);
    	SmartDashboard.putNumber("DriveRight DC: ", 5);
    	//Steering heading correction PID
		SmartDashboard.putNumber("Steering P: ", 0.0003);
    	SmartDashboard.putNumber("Steering I: ", 0.000);
    	SmartDashboard.putNumber("Steering D: ", 0.0);
    	SmartDashboard.putNumber("Steering Max: ", 0.2);
    	//Slider PID
		SmartDashboard.putNumber("Slider P: ", 0.00015);
    	SmartDashboard.putNumber("Slider I: ", 0.00001);
    	SmartDashboard.putNumber("Slider D: ", 0.0);
    	SmartDashboard.putNumber("Slider Max: ", 1.0);
    	SmartDashboard.putNumber("Slider Eps: ", 1.0);
    	SmartDashboard.putNumber("Slider DR: ", 200);
    	SmartDashboard.putNumber("Slider DC: ", 5);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		RobotMap.driveLeft1.setNeutralMode(NeutralMode.Coast);
		RobotMap.driveLeft2.setNeutralMode(NeutralMode.Coast);
		RobotMap.driveRight1.setNeutralMode(NeutralMode.Coast);
		RobotMap.driveRight2.setNeutralMode(NeutralMode.Coast);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		// drivetrain
		SmartDashboard.putNumber("Gyro.getYaw", RobotMap.ahrs.getYaw());
		SmartDashboard.putNumber("driveLeft.getPosition",
				Robot.drivetrain.getInverseLeftEncoderInches());
		SmartDashboard.putNumber("driveRight.getPosition",
				Robot.drivetrain.getRightEncoderInches());
		SmartDashboard.putNumber("driveLeft.voltage",
				RobotMap.driveLeft1.getMotorOutputVoltage());
		SmartDashboard.putNumber("driveRight.voltage",
				RobotMap.driveRight1.getMotorOutputVoltage());
		//intake
		SmartDashboard.putNumber("intakeRollerLeft.voltage",
				RobotMap.intakeRollerLeft.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerRight.voltage",
				RobotMap.intakeRollerRight.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerLeft.current",
				RobotMap.intakeRollerLeft.getOutputCurrent());
		SmartDashboard.putNumber("intakeRollerRight.current",
				RobotMap.intakeRollerRight.getOutputCurrent());
		//vaultboy
		SmartDashboard.putNumber("vaultBoyLeft.current",
				RobotMap.vaultBoyLeft.getOutputCurrent());
		SmartDashboard.putNumber("vaultBoyRight.current",
				RobotMap.vaultBoyRight.getOutputCurrent());
		//slider
		SmartDashboard.putNumber("slider.getPosition",
				RobotMap.slider.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("slider.voltage",
				RobotMap.slider.getMotorOutputVoltage());
		SmartDashboard.putNumber("slider.current",
				RobotMap.slider.getOutputCurrent());
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
		m_autonomousCommand = m_chooser.getSelected();
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		Robot.slider.resetEncoders();
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetGyro();
		
		RobotMap.driveLeft1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveLeft2.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight2.setNeutralMode(NeutralMode.Brake);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		// drivetrain
		SmartDashboard.putNumber("Gyro.getYaw", RobotMap.ahrs.getYaw());
		SmartDashboard.putNumber("driveLeft.getPosition",
				Robot.drivetrain.getInverseLeftEncoderInches());
		SmartDashboard.putNumber("driveRight.getPosition",
				Robot.drivetrain.getRightEncoderInches());
		SmartDashboard.putNumber("driveLeft.voltage",
				RobotMap.driveLeft1.getMotorOutputVoltage());
		SmartDashboard.putNumber("driveRight.voltage",
				RobotMap.driveRight1.getMotorOutputVoltage());
		//intake
		SmartDashboard.putNumber("intakeRollerLeft.voltage",
				RobotMap.intakeRollerLeft.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerRight.voltage",
				RobotMap.intakeRollerRight.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerLeft.current",
				RobotMap.intakeRollerLeft.getOutputCurrent());
		SmartDashboard.putNumber("intakeRollerRight.current",
				RobotMap.intakeRollerRight.getOutputCurrent());
		//vaultboy
		SmartDashboard.putNumber("vaultBoyLeft.current",
				RobotMap.vaultBoyLeft.getOutputCurrent());
		SmartDashboard.putNumber("vaultBoyRight.current",
				RobotMap.vaultBoyRight.getOutputCurrent());
		//slider
		SmartDashboard.putNumber("slider.getPosition",
				RobotMap.slider.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("slider.voltage",
				RobotMap.slider.getMotorOutputVoltage());
		SmartDashboard.putNumber("slider.current",
				RobotMap.slider.getOutputCurrent());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		Robot.drivetrain.resetEncoders();
		
		RobotMap.driveLeft1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveLeft2.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight1.setNeutralMode(NeutralMode.Brake);
		RobotMap.driveRight2.setNeutralMode(NeutralMode.Brake);
		
		new driveTeleop().start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// drivetrain
		SmartDashboard.putNumber("Gyro.getYaw", RobotMap.ahrs.getYaw());
		SmartDashboard.putNumber("driveLeft.getPosition",
				Robot.drivetrain.getInverseLeftEncoderInches());
		SmartDashboard.putNumber("driveRight.getPosition",
				Robot.drivetrain.getRightEncoderInches());
		SmartDashboard.putNumber("driveLeft.voltage",
				RobotMap.driveLeft1.getMotorOutputVoltage());
		SmartDashboard.putNumber("driveRight.voltage",
				RobotMap.driveRight1.getMotorOutputVoltage());
		//intake
		SmartDashboard.putNumber("intakeRollerLeft.voltage",
				RobotMap.intakeRollerLeft.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerRight.voltage",
				RobotMap.intakeRollerRight.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerLeft.current",
				RobotMap.intakeRollerLeft.getOutputCurrent());
		SmartDashboard.putNumber("intakeRollerRight.current",
				RobotMap.intakeRollerRight.getOutputCurrent());
		//vaultboy
		SmartDashboard.putNumber("vaultBoyLeft.current",
				RobotMap.vaultBoyLeft.getOutputCurrent());
		SmartDashboard.putNumber("vaultBoyRight.current",
				RobotMap.vaultBoyRight.getOutputCurrent());
		//slider
		SmartDashboard.putNumber("slider.getPosition",
				RobotMap.slider.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("slider.voltage",
				RobotMap.slider.getMotorOutputVoltage());
		SmartDashboard.putNumber("slider.current",
				RobotMap.slider.getOutputCurrent());
		
		if (OI.driverButton2.get()){
			RobotMap.slider.set(ControlMode.PercentOutput, OI.driverStick.getRawAxis(3)*0.2);
		}
		if (OI.driverButton3.get()){
			RobotMap.intakeRollerLeft.set(ControlMode.PercentOutput, OI.driverStick.getRawAxis(3)*0.4);
			RobotMap.intakeRollerRight.set(ControlMode.PercentOutput, -OI.driverStick.getRawAxis(3)*0.4);
			RobotMap.vaultBoyLeft.set(ControlMode.PercentOutput, OI.driverStick.getRawAxis(3)*0.4);
			RobotMap.vaultBoyRight.set(ControlMode.PercentOutput, OI.driverStick.getRawAxis(3)*0.4);
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
