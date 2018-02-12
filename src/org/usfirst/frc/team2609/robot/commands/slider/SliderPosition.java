package org.usfirst.frc.team2609.robot.commands.slider;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.subsystems.SimPID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SliderPosition extends Command {

		
	double sliderTarget;
	
	double sliderP;
	double sliderI;
	double sliderD;
	double sliderMax;
	double sliderEps;
	double sliderDR;
	double sliderF;
	int sliderDC;
	double sliderOutput;

	public SliderPosition(double sliderTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.slider);
    	this.sliderTarget = sliderTarget;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        
        sliderP = (double)SmartDashboard.getNumber("Slider P: ",0);
        sliderI = (double)SmartDashboard.getNumber("Slider I: ",0);
        sliderD = (double)SmartDashboard.getNumber("Slider D: ",0);
        sliderMax = (double)SmartDashboard.getNumber("Slider Max: ",0);
        sliderDC = (int)SmartDashboard.getNumber("Slider DC: ",0);
        sliderDR = SmartDashboard.getNumber("Slider DR: ",0);
        sliderEps = SmartDashboard.getNumber("Slider Eps: ",0);
        sliderF = SmartDashboard.getNumber("Slider F: ",0.5281);
        
        RobotMap.slider.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,0);
        RobotMap.slider.configMotionCruiseVelocity(1937, 0);
        RobotMap.slider.configMotionAcceleration(8000, 0);
        RobotMap.slider.config_kP(0,sliderP,0);
        RobotMap.slider.config_kI(0,sliderI,0);
        RobotMap.slider.config_kD(0,sliderD,0);
        RobotMap.slider.config_kF(0,sliderF,0);
    	RobotMap.slider.selectProfileSlot(0, 0);
    	RobotMap.slider.set(ControlMode.MotionMagic, sliderTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(RobotMap.slider.getSensorCollection().getQuadraturePosition()-sliderTarget)<=sliderDR;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.slider.sliderPower(0);
//    	System.out.println("Got to " + sliderTarget);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
