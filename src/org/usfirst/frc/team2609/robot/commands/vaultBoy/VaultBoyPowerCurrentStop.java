package org.usfirst.frc.team2609.robot.commands.vaultBoy;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VaultBoyPowerCurrentStop extends Command {

	double currentThreshold;
	double power;
	double currentLimit;
	boolean currentLatch;
	
    public VaultBoyPowerCurrentStop(double power,double currentLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vaultBoy);
    	this.power = power;
    	this.currentLimit = currentLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vaultBoy.vaultBoyPower(power,power);
    	currentLatch = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vaultBoy.vaultBoyPower(power,power);
    	if((Robot.vaultBoy.vaultBoyLeftCurrent()>currentLimit)&&(Robot.vaultBoy.vaultBoyLeftCurrent()>currentLimit)){
    		currentLatch = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((Robot.vaultBoy.vaultBoyLeftCurrent()<currentLimit)||(Robot.vaultBoy.vaultBoyLeftCurrent()<currentLimit))&&currentLatch);
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