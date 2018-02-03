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
	
	public void setDriveState(double leftPower, double rightPower){
		
	}
	
    public void resetDriveEncoders(){
    	RobotMap.vaultBoyLeft.getSensorCollection().setQuadraturePosition(0,0);
    	RobotMap.vaultBoyRight.getSensorCollection().setQuadraturePosition(0,0);
    }
    
    public double inverseLeftEncoder(){
		return -RobotMap.vaultBoyLeft.getSensorCollection().getQuadraturePosition();
    }
    
    public double inverseRightEncoder(){
		return -RobotMap.vaultBoyRight.getSensorCollection().getQuadraturePosition();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

