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
public class DriveStraightSpecial extends Command {

	private double leftPower;
	private double rightPower;
	SimPID driveLeft;
	SimPID driveRight;
	SimPID steering;
	
	double steeringP;
	double steeringI;
	double steeringD;
	double steeringTarget;
	double steeringMax;
	double steeringEps;
	double steeringOutput;

	double driveTarget;
	
	double driveLeftP;
	double driveLeftI;
	double driveLeftD;
	double driveLeftMax;
	double driveLeftEps;
	double driveLeftDR;
	int driveLeftDC;
	double driveLeftOutput;
	
	double driveRightP;
	double driveRightI;
	double driveRightD;
	double driveRightMax;
	double driveRightEps;
	double driveRightDR;
	int driveRightDC;
	double driveRightOutput;
	
	double absLeftPosition = Math.abs(Robot.drivetrain.getInverseLeftEncoderInches());
	double absRightPosition = Math.abs(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition());

	public DriveStraightSpecial(double driveTarget,double steeringTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    	this.driveTarget = driveTarget * Robot.inchesToTicks;
    	this.steeringTarget = steeringTarget;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.driveLeft = new SimPID();
        this.driveRight = new SimPID();
        this.steering = new SimPID();
        
    	steering.resetPreviousVal();
    	driveLeft.resetPreviousVal();
    	driveRight.resetPreviousVal();

        this.steering.setDesiredValue(steeringTarget);
        this.driveLeft.setDesiredValue(driveTarget);
        this.driveRight.setDesiredValue(driveTarget);
        
        steeringP = (double)SmartDashboard.getNumber("Steering P: ",0);
        steeringI = (double)SmartDashboard.getNumber("Steering I: ",0);
        steeringD = (double)SmartDashboard.getNumber("Steering D: ",0);
        steeringMax = (double)SmartDashboard.getNumber("Steering Max: ",0);
        this.steering.setConstants(steeringP, steeringI, steeringD);
        this.steering.setMaxOutput(steeringMax);
        
        driveLeftP = (double)SmartDashboard.getNumber("DriveLeft P: ",0);
        driveLeftI = (double)SmartDashboard.getNumber("DriveLeft I: ",0);
        driveLeftD = (double)SmartDashboard.getNumber("DriveLeft D: ",0);
        driveLeftMax = 0.6;
        driveLeftDC = 1;
        driveLeftDR = 1;
        driveLeftEps = 1;
        
        driveRightP = (double)SmartDashboard.getNumber("DriveRight P: ",0);
        driveRightI = (double)SmartDashboard.getNumber("DriveRight I: ",0);
        driveRightD = (double)SmartDashboard.getNumber("DriveRight D: ",0);
        driveRightMax = 0.6;
        driveRightDC = 1;
        driveRightDR = 1;
        driveRightEps = 1;
        
        this.driveLeft.setConstants(driveLeftP, driveLeftI, driveLeftD);
        this.driveLeft.setMaxOutput(driveLeftMax);
        this.driveLeft.setDoneRange(driveLeftDR);
        this.driveLeft.setMinDoneCycles(driveLeftDC);
        this.driveLeft.setErrorEpsilon(driveLeftEps);
        
        this.driveRight.setConstants(driveRightP, driveRightI, driveRightD);
        this.driveRight.setMaxOutput(driveRightMax);
        this.driveRight.setDoneRange(driveRightDR);
        this.driveRight.setMinDoneCycles(driveRightDC);
        this.driveRight.setErrorEpsilon(driveRightEps);

    	RobotMap.driveLeft1.configOpenloopRamp(1, 10);
    	RobotMap.driveLeft2.configOpenloopRamp(1, 10);
    	RobotMap.driveRight1.configOpenloopRamp(1, 10);
    	RobotMap.driveRight2.configOpenloopRamp(1, 10);
    	
    	absLeftPosition = Math.abs(Robot.drivetrain.getInverseLeftEncoderInches());
    	absRightPosition = Math.abs(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	absLeftPosition = Math.abs(Robot.drivetrain.getInverseLeftEncoderInches());
    	absRightPosition = Math.abs(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition());
    	
    	steeringOutput = -steering.calcPID(RobotMap.ahrs.getYaw());
    	driveLeftOutput = driveLeft.calcPID(Robot.drivetrain.getInverseLeftEncoderInches());
    	driveRightOutput = driveRight.calcPID(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition());
    	
    	if((absLeftPosition < Math.abs(driveTarget) - 10) && (absRightPosition < Math.abs(driveTarget) - 10)){
        	leftPower = driveLeftOutput - steeringOutput;
        	rightPower = driveRightOutput + steeringOutput;
    	}else {
    		leftPower = 0.2 - steeringOutput;
    		rightPower = 0.2 + steeringOutput;
    	}
    	
    	Robot.drivetrain.setDrive(DriveState.AUTON,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((absLeftPosition > Math.abs(driveTarget)) && (absRightPosition > Math.abs(driveTarget)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setDrive(DriveState.DISABLE,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setDrive(DriveState.DISABLE,0,0);
    }
}
