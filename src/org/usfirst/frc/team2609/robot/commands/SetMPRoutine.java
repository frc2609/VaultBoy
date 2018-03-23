package org.usfirst.frc.team2609.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.MPConstants;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.RobotMap;

/**
 *
 */
public class SetMPRoutine extends Command {
	MPRoutine routineSL, routineSR;
    public SetMPRoutine(MPRoutine routineSL, MPRoutine routineSR) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	MPConstants.sdGet();
    	this.routineSL = routineSL;
    	this.routineSR = routineSR;
    	RobotMap.mpRoutineL = routineSL;
    	RobotMap.mpRoutineR = routineSR;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	
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
