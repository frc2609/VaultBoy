package org.usfirst.frc.team2609.robot.commands.intakeRoller;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeRollerPower extends Command {

	double currentThreshold;
	double power;
	double timeInit;
	double timeCurrent;
	boolean timesUp;
	
    public IntakeRollerPower(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	this.currentThreshold = currentThreshold;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.intakeActivator.get() == DoubleSolenoid.Value.kForward){
        	
        	if (Timer.getFPGATimestamp() > timeInit + SmartDashboard.getNumber("rollertime", 0.1)) {
        		if ((Robot.intake.intakeRollerLeftCurrent() > currentThreshold) && (Robot.intake.intakeRollerRightCurrent() > currentThreshold)) {
    				timeInit = Timer.getFPGATimestamp();
    				Robot.intake.intakeRollerSetR(-power*0.75);
    				Robot.intake.intakeRollerSetL(-power*0.75);
    				System.out.println("BOTH");
    			}
        		else if (Robot.intake.intakeRollerLeftCurrent() > currentThreshold) {
    				timeInit = Timer.getFPGATimestamp();
    				Robot.intake.intakeRollerSetR(-power*0.75);
    				Robot.intake.intakeRollerSetL(-power*0.75);
//    				Robot.intakeRoller.intakeRollerSetL(power);
    			}
        		else if (Robot.intake.intakeRollerRightCurrent() > currentThreshold) {
    				timeInit = Timer.getFPGATimestamp();
//    				Robot.intakeRoller.intakeRollerSetR(power);
    				Robot.intake.intakeRollerSetR(-power*0.75);
    				Robot.intake.intakeRollerSetL(-power*0.75);
    			} else {
    				Robot.intake.intakeRollerSetL(power);
    				Robot.intake.intakeRollerSetR(power);
    			}
    		}
        	}else{
    			Robot.intake.intakeRollerSetR(0.2);
    			Robot.intake.intakeRollerSetL(0.2);
        		
        	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}