package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import enums.DriveState;

/**
 *
 */
public class VaultBoy extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void vaultBoyPower(double leftPower, double rightPower){
		RobotMap.vaultBoyLeft.set(ControlMode.PercentOutput, leftPower);
		RobotMap.vaultBoyRight.set(ControlMode.PercentOutput, rightPower);
	}
	
    public void resetVaultBoyEncoders(){
    	RobotMap.vaultBoyLeft.getSensorCollection().setQuadraturePosition(0,0);
    	RobotMap.vaultBoyRight.getSensorCollection().setQuadraturePosition(0,0);
    }
    
    public double inverseLeftEncoder(){
		return -RobotMap.vaultBoyLeft.getSensorCollection().getQuadraturePosition();
    }
    
    public double inverseRightEncoder(){
		return -RobotMap.vaultBoyRight.getSensorCollection().getQuadraturePosition();
    }
    
    public double vaultBoyLeftCurrent(){
		return RobotMap.vaultBoyLeft.getOutputCurrent();
    }
    
    public double vaultBoyRightCurrent(){
		return RobotMap.vaultBoyRight.getOutputCurrent();
    }

    public void initDefaultCommand() {
		RobotMap.vaultBoyLeft.set(ControlMode.PercentOutput, 0);
		RobotMap.vaultBoyRight.set(ControlMode.PercentOutput, 0);
    }
}

