package org.usfirst.frc.team2609.robot.commands.slider;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.subsystems.SimPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;

/**
 *
 */
public class sliderPosition extends Command {

	private double power;
	SimPID drive;
	
	double driveTarget;
	
	double driveP;
	double driveI;
	double driveD;
	double driveMax;
	double driveEps;
	double driveDR;
	int driveDC;
	double driveOutput;

	public sliderPosition(double driveTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.slider);
    	this.driveTarget = driveTarget;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.drive = new SimPID();
        
    	drive.resetPreviousVal();

        this.drive.setDesiredValue(driveTarget);
        
        driveP = (double)SmartDashboard.getNumber("Slider P: ",0);
        driveI = (double)SmartDashboard.getNumber("Slider I: ",0);
        driveD = (double)SmartDashboard.getNumber("Slider D: ",0);
        driveMax = (double)SmartDashboard.getNumber("Slider Max: ",0);
        driveDC = (int)SmartDashboard.getNumber("Slider DC: ",0);
        driveDR = SmartDashboard.getNumber("Slider DR: ",0);
        driveEps = SmartDashboard.getNumber("Slider Eps: ",0);
        
        this.drive.setConstants(driveP, driveI, driveD);
        this.drive.setMaxOutput(driveMax);
        this.drive.setDoneRange(driveDR);
        this.drive.setMinDoneCycles(driveDC);
        this.drive.setErrorEpsilon(driveEps);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveOutput = drive.calcPID(RobotMap.slider.getSensorCollection().getQuadraturePosition());
    	Robot.slider.sliderPower(driveOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return drive.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.slider.sliderPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setDriveState(DriveState.DISABLE,0,0);
    }
}
