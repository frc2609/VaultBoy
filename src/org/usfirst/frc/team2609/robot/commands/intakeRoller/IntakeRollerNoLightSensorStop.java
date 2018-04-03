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
public class IntakeRollerNoLightSensorStop extends Command {

	double power;
	double timeInit;
	double cubeCounter;
	double timeCurrent;
	boolean timesUp;
	
    public IntakeRollerNoLightSensorStop(double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	power = this.power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.intakeRollerSetR(power);
    	Robot.intake.intakeRollerSetL(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!Robot.intake.intakeRollerCubeSensor()){
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
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}