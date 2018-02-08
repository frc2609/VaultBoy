package org.usfirst.frc.team2609.robot.commands.slider;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.subsystems.SimPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class sliderPosition extends Command {

	SimPID slider;
	
	double sliderTarget;
	
	double sliderP;
	double sliderI;
	double sliderD;
	double sliderMax;
	double sliderEps;
	double sliderDR;
	int sliderDC;
	double sliderOutput;

	public sliderPosition(double sliderTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.slider);
    	this.sliderTarget = sliderTarget;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.slider = new SimPID();
        
    	slider.resetPreviousVal();

        this.slider.setDesiredValue(sliderTarget);
        
        sliderP = (double)SmartDashboard.getNumber("Slider P: ",0);
        sliderI = (double)SmartDashboard.getNumber("Slider I: ",0);
        sliderD = (double)SmartDashboard.getNumber("Slider D: ",0);
        sliderMax = (double)SmartDashboard.getNumber("Slider Max: ",0);
        sliderDC = (int)SmartDashboard.getNumber("Slider DC: ",0);
        sliderDR = SmartDashboard.getNumber("Slider DR: ",0);
        sliderEps = SmartDashboard.getNumber("Slider Eps: ",0);
        
        this.slider.setConstants(sliderP, sliderI, sliderD);
        this.slider.setMaxOutput(sliderMax);
        this.slider.setDoneRange(sliderDR);
        this.slider.setMinDoneCycles(sliderDC);
        this.slider.setErrorEpsilon(sliderEps);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	sliderOutput = slider.calcPID(RobotMap.slider.getSensorCollection().getQuadraturePosition());
    	Robot.slider.sliderPower(sliderOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return slider.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.slider.sliderPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
