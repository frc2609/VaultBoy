package org.usfirst.frc.team2609.robot.commands.intakeRoller;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class intakeRollerPower extends Command {

	double currentThreshold;
	double power;
	
    public intakeRollerPower(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.slider);
    	this.currentThreshold = currentThreshold;
    	this.currentThreshold = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.slider.sliderCurrent() < currentThreshold){
        	Robot.intakeRoller.setIntakePower(power,power);
    	}else{
        	Robot.intakeRoller.setIntakePower(0,0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//((Robot.vaultBoy.vaultBoyLeftCurrent() > 30) && (Robot.vaultBoy.vaultBoyRightCurrent() > 30));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeRoller.setIntakePower(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}