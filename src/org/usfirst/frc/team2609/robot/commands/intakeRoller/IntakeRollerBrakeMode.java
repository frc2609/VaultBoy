package org.usfirst.frc.team2609.robot.commands.intakeRoller;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRollerBrakeMode extends Command {

	boolean brakeMode;
	
    public IntakeRollerBrakeMode(boolean brakeMode) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	this.brakeMode = brakeMode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeRoller.intakeRollerBrakeMode(brakeMode);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}