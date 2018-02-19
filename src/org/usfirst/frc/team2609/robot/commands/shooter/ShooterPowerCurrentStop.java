package org.usfirst.frc.team2609.robot.commands.shooter;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterPowerCurrentStop extends Command {

	double currentThreshold;
	double power;
	double currentLimit;
	boolean currentLatch;
	
    public ShooterPowerCurrentStop(double power,double currentLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.power = power;
    	this.currentLimit = currentLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shooterLeft.set(power);
    	RobotMap.shooterRight.set(-power);
    	currentLatch = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.shooterLeft.set(power);
    	RobotMap.shooterRight.set(-power);
    	if(RobotMap.pdp.getVoltage()<=10){
    		currentLimit++;
    	}
    	if(RobotMap.pdp.getVoltage()<=8){
    		currentLimit++;
    	}
    	if((RobotMap.shooterLeft.getOutputCurrent()>currentLimit)&&(RobotMap.shooterRight.getOutputCurrent()>currentLimit)){
    		currentLatch = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((RobotMap.shooterLeft.getOutputCurrent()<currentLimit)||(RobotMap.shooterRight.getOutputCurrent()<currentLimit))&&currentLatch);
    }

    // Called once after isFinished returns true
    protected void end() {
    	currentLatch = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}