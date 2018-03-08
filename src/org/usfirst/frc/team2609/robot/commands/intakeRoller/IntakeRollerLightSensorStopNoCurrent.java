package org.usfirst.frc.team2609.robot.commands.intakeRoller;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeRollerLightSensorStopNoCurrent extends Command {

	double currentThreshold;
	double powerL;
	double powerR;
	double timeInit;
	double cubeCounter;
	double timeCurrent;
	boolean timesUp;
	
    public IntakeRollerLightSensorStopNoCurrent(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	this.currentThreshold = currentThreshold;
    	this.powerL = power;
    	this.powerR = power;
    }
    public IntakeRollerLightSensorStopNoCurrent(double powerL,double powerR,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	this.currentThreshold = currentThreshold;
    	this.powerL = powerL;
    	this.powerR = powerR;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
				Robot.intake.intakeRollerSetL(powerL);
				Robot.intake.intakeRollerSetR(powerR);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.intake.intakeRollerCubeSensor()){
    		cubeCounter++;
    	}
    	else{
    		cubeCounter = 0;
    	}
    	
    	if (cubeCounter >= 10) {
    		return true;
		}
    	else{
    		return false;
    	}
        //return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}