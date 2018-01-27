package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import enums.DriveState;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setDriveState(DriveState desiredState,double leftPower, double rightPower){
		switch(desiredState){
		case TELEOP:
	        RobotMap.driveLeft1.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,rightPower);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,rightPower);
			break;
		case AUTON:
			RobotMap.driveLeft1.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,rightPower);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,rightPower);
			break;
		case DISABLE:
	        RobotMap.driveLeft1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,0);
//			new driveTeleop().start();
			break;
		default:
	        RobotMap.driveLeft1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,0);
			break;
		}
	}
	
    public void resetDriveEncoders(){
    	RobotMap.driveLeft1.getSensorCollection().setQuadraturePosition(0,0);
    	RobotMap.driveRight1.getSensorCollection().setQuadraturePosition(0,0);
    }

    public void resetGyro(){
    	RobotMap.ahrs.zeroYaw();
    }
    
    public double inverseLeftEncoder(){
		return -RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition();
    }
    
    public double inverseRightEncoder(){
		return -RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

