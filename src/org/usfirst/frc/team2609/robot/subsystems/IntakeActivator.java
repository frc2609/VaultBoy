package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.IntakeActivatorState;

/**
 *
 */
public class IntakeActivator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setIntakeActivatorState(IntakeActivatorState desiredState){
		switch(desiredState){
		case IN:
			RobotMap.intakeActivator.set(DoubleSolenoid.Value.kReverse);
			break;
		case OUT:
			RobotMap.intakeActivator.set(DoubleSolenoid.Value.kForward);
			break;
		default:
			RobotMap.shooterActivator.set(DoubleSolenoid.Value.kReverse);
		}
	}

    public void initDefaultCommand() {
    	
    }
}

