package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ShooterActivatorState;

/**
 *
 */
public class ShooterActivator extends Subsystem {

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

    public void initDefaultCommand() {
    	
    }
}

