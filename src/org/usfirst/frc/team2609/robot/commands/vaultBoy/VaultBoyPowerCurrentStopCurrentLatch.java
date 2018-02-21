package org.usfirst.frc.team2609.robot.commands.vaultBoy;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

// author: Akos
// who didnt approve of this at 1045 on stop build: Greg and Reid
public class VaultBoyPowerCurrentStopCurrentLatch extends Command {

	double currentThreshold;
	double power;
	double currentLimit;
	double latchTime;
	boolean currentLatch;
	
    public VaultBoyPowerCurrentStopCurrentLatch(double power,double currentLimit) {
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
    	latchTime = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vaultBoy.vaultBoyPower(power,power);
    	if((Robot.vaultBoy.vaultBoyLeftCurrent()>currentLimit)&&(Robot.vaultBoy.vaultBoyLeftCurrent()>currentLimit)){
    		currentLatch = true;
    		latchTime = Timer.getFPGATimestamp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((Robot.vaultBoy.vaultBoyLeftCurrent()<currentLimit)||(Robot.vaultBoy.vaultBoyLeftCurrent()<currentLimit))
        		&&currentLatch&&((Math.abs(latchTime-Timer.getFPGATimestamp()))<1));
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