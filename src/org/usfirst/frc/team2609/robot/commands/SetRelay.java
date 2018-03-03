package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetRelay extends Command {
	boolean on;
    public SetRelay(boolean on) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.on = on;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(on){
    		RobotMap.led.set(Value.kForward);
    	}else{
    		RobotMap.led.set(Value.kForward);
    		
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
