package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.OI;
import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import enums.DriveActivatorState;
import enums.DriveState;

/**
 *
 */
public class DriveTeleopChezy extends Command {

	public DriveTeleopChezy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveLeft1.configOpenloopRamp(0.6, 10);
    	RobotMap.driveLeft2.configOpenloopRamp(0.6, 10);
    	RobotMap.driveRight1.configOpenloopRamp(0.6, 10);
    	RobotMap.driveRight2.configOpenloopRamp(0.6, 10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double X = OI.driverStick.getRawAxis(0);
        double Y = -OI.driverStick.getRawAxis(1);
        if (Robot.m_oi.driverButton3.get()){
            Y = OI.driverStick.getRawAxis(1);
        }
        
        double deadzone = 0.15;
        
        if(Math.abs(X)<deadzone && Math.abs(Y)<deadzone){
        	X	=0;
        	Y 	=0;
        }

    	Robot.drivetrain.setDrive(DriveState.TELEOPCHEZY,X,Y);
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
