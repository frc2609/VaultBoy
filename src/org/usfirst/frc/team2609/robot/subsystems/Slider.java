package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.MP.Loop;
import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;

/**
 *
 */
public class Slider extends Subsystem {
	public boolean initialSensorCheck(){

    	int sensorSStatus = RobotMap.slider.getSensorCollection().getPulseWidthRiseToRiseUs();
    	if (sensorSStatus == 0) {
            DriverStation.reportError("Could not detect Slider encoder: ", false);
            return true;
        }else{
        	return false;
        }
    }
	
	private final org.usfirst.frc.team2609.MP.Loop mLoop = new Loop() {
		@Override
		public void onStart(){
			System.out.println("Starting Slider loop");
		}
		@Override
		public void onLoop(){
			synchronized (Slider.this){
//				Robot.logger.logTele();
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
    public void outputSd(){

		//slider
		SmartDashboard.putNumber("slider.getPosition",
				RobotMap.slider.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("slider.voltage",
				RobotMap.slider.getMotorOutputVoltage());
		SmartDashboard.putNumber("slider.current",
				RobotMap.slider.getOutputCurrent());
		SmartDashboard.putNumber("slider.error",
				RobotMap.slider.getClosedLoopError(0));
		SmartDashboard.putNumber("slider.getSelectedSensorPosition0",
				RobotMap.slider.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("slider.getSelectedSensorVel",
				RobotMap.slider.getSelectedSensorVelocity(0));
		
    }
    public void initSd(){
    	//Slider PID
    			SmartDashboard.putNumber("Slider P: ", 0.5);
    	    	SmartDashboard.putNumber("Slider I: ", 0.0001);
    	    	SmartDashboard.putNumber("Slider D: ", 0.0);
    	    	SmartDashboard.putNumber("Slider F: ", 0.32);
    	    	SmartDashboard.putNumber("Slider Max: ", 1.0);
    	    	SmartDashboard.putNumber("Slider Eps: ", 1.0);
    	    	SmartDashboard.putNumber("Slider DR: ", 100);
    	    	SmartDashboard.putNumber("Slider DC: ", 5);
    }
    
    
}