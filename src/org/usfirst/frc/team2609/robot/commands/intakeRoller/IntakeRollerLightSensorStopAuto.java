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
public class IntakeRollerLightSensorStopAuto extends Command {

	double currentThreshold;
	double powerL;
	double powerR;
	double timeInit;
	double cubeCounter;
	double timeCurrent;
	boolean timesUp;
	boolean disableCurrentSense = false;
	
    public IntakeRollerLightSensorStopAuto(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	this.currentThreshold = currentThreshold;
    	this.powerL = power;
    	this.powerR = power;
    }
    public IntakeRollerLightSensorStopAuto(double powerL,double powerR,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	this.currentThreshold = currentThreshold;
    	this.powerL = powerL;
    	this.powerR = powerR;
    }
    public IntakeRollerLightSensorStopAuto(double powerL,double powerR,boolean disableCurrent) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	this.currentThreshold = -1000; //hehe
    	this.disableCurrentSense = true; // to make sure you dont pass on (double,double,false)
    	this.powerL = powerL;
    	this.powerR = powerR;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(disableCurrentSense){
			Robot.intakeRoller.intakeRollerSetL(powerL);
			Robot.intakeRoller.intakeRollerSetR(powerR);
    		
    	}else{
    		
    	if (Timer.getFPGATimestamp() > timeInit + SmartDashboard.getNumber("rollertime", 0.1)) {
    		if ((Robot.intakeRoller.intakeRollerLeftCurrent() > currentThreshold) && (Robot.intakeRoller.intakeRollerRightCurrent() > currentThreshold)) {
				timeInit = Timer.getFPGATimestamp();
				Robot.intakeRoller.intakeRollerSetR(0);
				Robot.intakeRoller.intakeRollerSetL(0);
				System.out.println("BOTH");
			}
    		else if (Robot.intakeRoller.intakeRollerLeftCurrent() > currentThreshold) {
				timeInit = Timer.getFPGATimestamp();
				Robot.intakeRoller.intakeRollerSetR(0);
				Robot.intakeRoller.intakeRollerSetL(0);
				System.out.println("L");
//				Robot.intakeRoller.intakeRollerSetL(power);
			}
    		else if (Robot.intakeRoller.intakeRollerRightCurrent() > currentThreshold) {
				timeInit = Timer.getFPGATimestamp();
//				Robot.intakeRoller.intakeRollerSetR(power);
				Robot.intakeRoller.intakeRollerSetR(0);
				Robot.intakeRoller.intakeRollerSetL(0);
				System.out.println("R");
			} else {
				Robot.intakeRoller.intakeRollerSetL(powerL);
				Robot.intakeRoller.intakeRollerSetR(powerR);
			}
		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.intakeRoller.intakeRollerCubeSensor()){
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