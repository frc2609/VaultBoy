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
    
    public double vaultBoyLeftCurrent(){
		return RobotMap.vaultBoyLeft.getOutputCurrent();
    }
    
    public double vaultBoyRightCurrent(){
		return RobotMap.vaultBoyRight.getOutputCurrent();
    }

    public void initDefaultCommand() {
    }
}

