package org.usfirst.frc.team2609.robot.commands.intakeRoller;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeRollerLightSensorStop extends Command {

	double currentThreshold;
	double power;
	double timeInit;
	double cubeCounter;
	double timeCurrent;
	boolean timesUp;
	
    public IntakeRollerLightSensorStop(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	this.currentThreshold = currentThreshold;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
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
//				Robot.intakeRoller.intakeRollerSetL(power);
			}
    		else if (Robot.intakeRoller.intakeRollerRightCurrent() > currentThreshold) {
				timeInit = Timer.getFPGATimestamp();
//				Robot.intakeRoller.intakeRollerSetR(power);
				Robot.intakeRoller.intakeRollerSetR(0);
				Robot.intakeRoller.intakeRollerSetL(0);
			} else {
				Robot.intakeRoller.intakeRollerSetL(power);
				Robot.intakeRoller.intakeRollerSetR(power);
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
    	
    	if (cubeCounter == 7) {
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