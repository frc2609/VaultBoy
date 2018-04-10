package org.usfirst.frc.team2609.robot.commands.shooter;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterPowerSensorStop extends Command {

	double power;
	int sensorCounter;
	int sensorLimit;
	boolean isOut;
	boolean sensorLatch;
	
    public ShooterPowerSensorStop(double power,int sensorLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.power = power;
    	this.sensorLimit = sensorLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.shooterLeft.set(power);
    	RobotMap.shooterRight.set(-power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.shooterLeft.set(power);
    	RobotMap.shooterRight.set(-power);
    	if(RobotMap.shooterSensor.get()){
    		sensorCounter++;
    	}else{
    		if(sensorLatch){
    			isOut = true;
    		}else{
    			sensorCounter = 0;
    		}
    	}
    	sensorLatch = (sensorCounter>=sensorLimit)? true: false;
    	
    	
    	System.out.println(sensorCounter);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return sensorLatch;
    }

    // Called once after isFinished returns true
    protected void end() {
    	sensorLatch = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}