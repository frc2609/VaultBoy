package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;

/**
 *
 */
public class IntakeRoller extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setIntakePower(double leftPower, double rightPower){
		RobotMap.intakeRollerLeft.set(ControlMode.PercentOutput, leftPower);
		RobotMap.intakeRollerRight.set(ControlMode.PercentOutput, -rightPower);
	}
    
    public double intakeRollerLeftCurrent(){
    	return RobotMap.intakeRollerLeft.getOutputCurrent();
    }
    
    public double intakeRollerRightCurrent(){
    	return RobotMap.intakeRollerRight.getOutputCurrent();
    }

    public boolean intakeRollerCubeSensor(){
    	SmartDashboard.putBoolean("CubeDetect", RobotMap.cubeSensor.get());
    	return RobotMap.cubeSensor.get();
    }
    
    public void initDefaultCommand() {
		RobotMap.intakeRollerLeft.set(ControlMode.PercentOutput, 0);
		RobotMap.intakeRollerRight.set(ControlMode.PercentOutput, 0);
    }
}

