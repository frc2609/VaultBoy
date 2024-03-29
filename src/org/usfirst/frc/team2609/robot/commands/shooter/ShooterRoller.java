package org.usfirst.frc.team2609.robot.commands.shooter;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import enums.DriveActivatorState;

/**
 *
 */
public class ShooterRoller extends Command {
	double speed;
    public ShooterRoller(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shooterLeft.set(speed);
    	RobotMap.shooterRight.set(-speed);
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
    	if(speed>0){
    		RobotMap.teleopState = DriveActivatorState.DISABLED;
    	}else{
    		RobotMap.teleopState = DriveActivatorState.ACTIVE;
    		
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
