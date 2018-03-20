package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;
import enums.IntakeActivatorState;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setIntakeActivatorState(IntakeActivatorState desiredState){
		switch(desiredState){
		case IN:
			RobotMap.intakeActivator.set(DoubleSolenoid.Value.kForward);
			break;
		case OUT:
			RobotMap.intakeActivator.set(DoubleSolenoid.Value.kReverse);
			break;
		default:
			RobotMap.shooterActivator.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public void setIntakePower(double leftPower, double rightPower){
		RobotMap.intakeRollerLeft.set(ControlMode.PercentOutput, leftPower);
		RobotMap.intakeRollerRight.set(ControlMode.PercentOutput, rightPower);
	}
    
    public double intakeRollerLeftCurrent(){
    	return RobotMap.intakeRollerLeft.getOutputCurrent();
    }
    
    public double intakeRollerRightCurrent(){
    	return RobotMap.intakeRollerRight.getOutputCurrent();
    }
    public void intakeRollerSetR(double speed){
    	RobotMap.intakeRollerRight.set(speed);
    }
    public void intakeRollerSetL(double speed){
    	RobotMap.intakeRollerLeft.set(speed);
    }
    
    public void intakeRollerBrakeMode(boolean brakeMode){
    	if(brakeMode){
    		RobotMap.intakeRollerLeft.setNeutralMode(NeutralMode.Brake);
    		RobotMap.intakeRollerRight.setNeutralMode(NeutralMode.Brake);
    	}else{
    		RobotMap.intakeRollerLeft.setNeutralMode(NeutralMode.Coast);
    		RobotMap.intakeRollerRight.setNeutralMode(NeutralMode.Coast);
    	}
    }

    public boolean intakeRollerCubeSensor(){
    	SmartDashboard.putBoolean("CubeDetect", RobotMap.cubeSensor.get());
    	return RobotMap.cubeSensor.get();
    }
    
    public void initDefaultCommand() {
		RobotMap.intakeRollerLeft.set(ControlMode.PercentOutput, 0);
		RobotMap.intakeRollerRight.set(ControlMode.PercentOutput, 0);
    }
    public void outputSd(){
		//intake
		SmartDashboard.putNumber("intakeRollerLeft.voltage",
				RobotMap.intakeRollerLeft.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerRight.voltage",
				RobotMap.intakeRollerRight.getMotorOutputVoltage());
		SmartDashboard.putNumber("intakeRollerLeft.current",
				RobotMap.intakeRollerLeft.getOutputCurrent());
		SmartDashboard.putNumber("intakeRollerRight.current",
				RobotMap.intakeRollerRight.getOutputCurrent());
    }
    
    public boolean cubeDetect(){
		//read 2 sensors
		return (RobotMap.cubeSensor.get() && RobotMap.cubeSensor2.get());
    }
    
    public void cubeDisplay(){
    	if (cubeDetect()){
    		RobotMap.canifier.setPWMOutput(1,100);
    		RobotMap.canifier.setPWMOutput(0,0);
    	}
    	else{
    		RobotMap.canifier.setPWMOutput(0,100);
    		RobotMap.canifier.setPWMOutput(1,0);
    	}
    }
}

