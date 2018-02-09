package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.subsystems.SimPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;

/**
 *
 */
public class driveStraightTrapezoid extends Command {

	private double leftPower;
	private double rightPower;
	SimPID steering;
	
	double steeringP;
	double steeringI;
	double steeringD;
	double steeringTarget;
	double steeringMax;
	double steeringEps;
	double steeringOutput;

	double driveTarget;

	public driveStraightTrapezoid(double driveTarget,double steeringTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    	this.driveTarget = driveTarget * Robot.inchesToTicks;
    	this.steeringTarget = steeringTarget;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.steering = new SimPID();
        
    	steering.resetPreviousVal();

        this.steering.setDesiredValue(steeringTarget);
        
        steeringP = (double)SmartDashboard.getNumber("Steering P: ",0);
        steeringI = (double)SmartDashboard.getNumber("Steering I: ",0);
        steeringD = (double)SmartDashboard.getNumber("Steering D: ",0);
        steeringMax = (double)SmartDashboard.getNumber("Steering Max: ",0);
        this.steering.setConstants(steeringP, steeringI, steeringD);
        this.steering.setMaxOutput(steeringMax);

    	RobotMap.driveLeft1.configOpenloopRamp(1, 10);
    	RobotMap.driveLeft2.configOpenloopRamp(1, 10);
    	RobotMap.driveRight1.configOpenloopRamp(1, 10);
    	RobotMap.driveRight2.configOpenloopRamp(1, 10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	steeringOutput = -steering.calcPID(RobotMap.ahrs.getYaw());
    	if(Robot.drivetrain.getInverseLeftEncoderInchesTemp() < 10){
        	leftPower = (0.07*Robot.drivetrain.getInverseLeftEncoderInchesTemp())+0.2;
        	rightPower = (0.07*Robot.drivetrain.getInverseLeftEncoderInchesTemp())+0.2;
    	}else if (Robot.drivetrain.getInverseLeftEncoderInchesTemp() < (driveTarget - 10)){
        	leftPower = 0.9;
        	rightPower = 0.9;
    	}else{
        	leftPower = (-0.07*Robot.drivetrain.getInverseLeftEncoderInchesTemp())+(0.2+(0.07*driveTarget));
        	rightPower = (-0.07*Robot.drivetrain.getInverseLeftEncoderInchesTemp())+(0.2+(0.07*driveTarget));
    	}
    	
    	Robot.drivetrain.setDriveState(DriveState.AUTON,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setDriveState(DriveState.DISABLE,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setDriveState(DriveState.DISABLE,0,0);
    }
}
