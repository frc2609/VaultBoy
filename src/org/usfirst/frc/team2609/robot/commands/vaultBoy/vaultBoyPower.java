package org.usfirst.frc.team2609.robot.commands.vaultBoy;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class vaultBoyPower extends Command {

	double currentThreshold;
	double power;
	
    public vaultBoyPower(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vaultBoy);
    	this.currentThreshold = currentThreshold;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vaultBoy.vaultBoyPower(power,power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.vaultBoy.vaultBoyPower(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}