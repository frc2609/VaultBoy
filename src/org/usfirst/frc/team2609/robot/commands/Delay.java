package org.usfirst.frc.team2609.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {
	double time,startTime;
    public Delay(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp()>=(startTime+time));
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("DELAY DONE");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
