package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.OI;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleOP extends Command {

    public RumbleOP() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.driverStick.setRumble(RumbleType.kLeftRumble, 1);
    	OI.driverStick.setRumble(RumbleType.kRightRumble, 1);
    	setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//OI.operatorStick.setRumble(RumbleType.kLeftRumble, 0);
    	//OI.operatorStick.setRumble(RumbleType.kRightRumble, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
