package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;
import enums.ShooterActivatorState;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void setShooterActivatorState(ShooterActivatorState desiredState){
		switch(desiredState){
		case UP:
			RobotMap.shooterActivator.set(DoubleSolenoid.Value.kReverse);
			break;
		case DOWN:
//			if ((Math.abs(RobotMap.slider.getSelectedSensorPosition(0)-500))<=100 && RobotMap.intakeActivator.get() == DoubleSolenoid.Value.kReverse){
				RobotMap.shooterActivator.set(DoubleSolenoid.Value.kForward);
//			}else{
//				System.out.println("SLIDER OUT");
//			}
			break;
		default:
			RobotMap.shooterActivator.set(DoubleSolenoid.Value.kReverse);
		}
	}
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
		RobotMap.vaultBoyLeft.set(ControlMode.PercentOutput, 0);
		RobotMap.vaultBoyRight.set(ControlMode.PercentOutput, 0);
    }
    public void outputSd(){

    	SmartDashboard.putNumber("ShooterL curr: ", RobotMap.shooterLeft.getOutputCurrent());
    	SmartDashboard.putNumber("ShooterR curr: ", RobotMap.shooterRight.getOutputCurrent());
    }
}

