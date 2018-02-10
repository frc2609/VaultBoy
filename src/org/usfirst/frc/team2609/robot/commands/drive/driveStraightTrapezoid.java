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

	double setpoint1;
	double setpoint2;
	double setpointEnd;
	double powerStart;
	double powerCruise;
	double powerEnd;

	public driveStraightTrapezoid(double setpoint1,double setpoint2,double setpointEnd,double powerStart,double powerCruise,double powerEnd,double steeringTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    	this.setpoint1 = setpoint1;
    	this.setpoint2 = setpoint2;
    	this.setpointEnd = setpointEnd;
    	this.powerStart = powerStart;
    	this.powerCruise = powerCruise;
    	this.powerEnd = powerEnd;
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	steeringOutput = -steering.calcPID(RobotMap.ahrs.getYaw());
    	if(Robot.drivetrain.getInverseLeftEncoderInchesTemp() < setpoint1){
        	leftPower = (((powerCruise - powerStart)/setpoint1)*Robot.drivetrain.getInverseLeftEncoderInchesTemp())+powerStart;
        	rightPower = (((powerCruise - powerStart)/setpoint1)*Robot.drivetrain.getRightEncoderInches())+powerStart;
    	}else if (Robot.drivetrain.getInverseLeftEncoderInchesTemp() < setpoint2){
        	leftPower = powerCruise;
        	rightPower = powerCruise;
    	}else{
        	leftPower = (((powerEnd - powerCruise)/setpoint2)*Robot.drivetrain.getInverseLeftEncoderInchesTemp())
        			+((((powerEnd - powerCruise)/setpoint2)*setpointEnd)-powerEnd);
        	rightPower = (((powerEnd - powerCruise)/setpoint2)*Robot.drivetrain.getRightEncoderInches())
        			+((((powerEnd - powerCruise)/setpoint2)*setpointEnd)-powerEnd);
    	}
    	
    	Robot.drivetrain.setDriveState(DriveState.AUTON,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.drivetrain.getInverseLeftEncoderInchesTemp() > setpointEnd);
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
