package org.usfirst.frc.team2609.robot.commands.vaultBoy;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VaultBoyPowerSensorStop extends Command {

	double currentThreshold;
	double power;
	double currentLimit;
	double latchTime;
	boolean currentLatch;
	
    public VaultBoyPowerSensorStop(double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vaultBoy);
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.vaultBoy.vaultBoyPower(power,power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vaultBoy.vaultBoyPower(power,power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !RobotMap.cubeSensorArm.get();
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