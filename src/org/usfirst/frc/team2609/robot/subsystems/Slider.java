package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.MP.Loop;
import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import enums.DriveState;

/**
 *
 */
public class Slider extends Subsystem {
	
	
	private final org.usfirst.frc.team2609.MP.Loop mLoop = new Loop() {
		@Override
		public void onStart(){
			System.out.println("Starting Slider loop");
		}
		@Override
		public void onLoop(){
			synchronized (Slider.this){
//				Robot.logger.logMM();
			}
		}
		@Override
		public void onStop(){
			System.out.println("Ending Slider loop");
		}
	};

	public Loop getLooper(){
		return mLoop;
	}
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void sliderPower(double power){
        RobotMap.slider.set(ControlMode.PercentOutput,power);
	}
	
    public void resetEncoders(){
    	RobotMap.slider.getSensorCollection().setQuadraturePosition(0,0);
    }
    
    public double inverseEncoder(){
		return -RobotMap.slider.getSensorCollection().getQuadraturePosition();
    }
    
    public double sliderCurrent(){
    	return RobotMap.slider.getOutputCurrent();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}