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
public class DriveTurn extends Command {

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
	
	double driveLeftP;
	double driveLeftI;
	double driveLeftD;
	double driveLeftTarget;
	double driveLeftMax;
	double driveLeftEps;
	double driveLeftDR;
	int driveLeftDC;
	double driveLeftOutput;
	
	double driveRightP;
	double driveRightI;
	double driveRightD;
	double driveRightTarget;
	double driveRightMax;
	double driveRightEps;
	double driveRightDR;
	int driveRightDC;
	double driveRightOutput;
	
	int turnDirection;
	
	public DriveTurn(double drivePower,double steeringTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    	this.steeringTarget = steeringTarget;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.driveLeft = new SimPID();
        this.driveRight = new SimPID();
        this.steering = new SimPID();
        
    	steering.resetPreviousVal();
        this.steering.setDesiredValue(steeringTarget);
        this.driveLeft.setDesiredValue(0);				//keep wheel stopped
        this.driveRight.setDesiredValue(0);				//keep wheel stopped
        
        steeringP = 0.012;
        steeringI = 0.0005;
        steeringD = 0;
        this.steering.setConstants(steeringP, steeringI, steeringD);
        this.steering.setMaxOutput(1.0);
        this.steering.setDoneRange(1.0);
        this.steering.setMinDoneCycles(10);
        
        driveLeftP = (double)SmartDashboard.getNumber("DriveLeft P: ",0);
        driveLeftI = (double)SmartDashboard.getNumber("DriveLeft I: ",0);
        driveLeftD = (double)SmartDashboard.getNumber("DriveLeft D: ",0);
        driveLeftMax = 0.6;
        driveLeftDC = 0;
        driveLeftDR = 0.1;
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
    	
    	if (RobotMap.ahrs.getYaw()<steeringTarget){
    		turnDirection = 0;	//right turn
    	}
    	else{
    		turnDirection = 1;	//left turn
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	steeringOutput = steering.calcPID(RobotMap.ahrs.getYaw());
    	if (turnDirection == 0){
    		if(RobotMap.ahrs.getYaw() < steeringTarget - 10){
            	leftPower =  steeringOutput;
            	rightPower = driveRight.calcPID(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition());
    		}else{
            	leftPower =  0.15;
            	rightPower = driveRight.calcPID(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition());
    		}
    	}
    	else{
    		if(RobotMap.ahrs.getYaw() > steeringTarget + 10){
        		leftPower = driveLeft.calcPID(RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition());
            	rightPower = -steeringOutput;
    		}else{
        		leftPower = driveLeft.calcPID(RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition());
            	rightPower = 0.15;
    		}
    	}
    	Robot.drivetrain.setDrive(DriveState.AUTON,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (turnDirection == 0){
    		return (RobotMap.ahrs.getYaw() > steeringTarget);
    	}else{
    		return (RobotMap.ahrs.getYaw() < steeringTarget);
    	}
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
