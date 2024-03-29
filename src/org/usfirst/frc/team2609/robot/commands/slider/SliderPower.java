package org.usfirst.frc.team2609.robot.commands.slider;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SliderPower extends Command {

	double currentThreshold;
	double power;
	
    public SliderPower(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.slider);
    	this.currentThreshold = currentThreshold;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.slider.sliderPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.slider.sliderCurrent() > currentThreshold && RobotMap.slider.getSelectedSensorVelocity(0)<30);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.slider.sliderPower(0);
    	RobotMap.isHomed = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}