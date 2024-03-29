package org.usfirst.frc.team2609.robot.commands.intakeRoller;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRollerPowerVaultBoyStop extends Command {

	double currentThreshold;
	double power;
	double timeInit;
	double timeCurrent;
	boolean timesUp;
	
    public IntakeRollerPowerVaultBoyStop(double power,double currentThreshold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	this.currentThreshold = currentThreshold;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (Timer.getFPGATimestamp() > timeInit + 0.1) {
			if ((Robot.intakeRoller.intakeRollerLeftCurrent() < currentThreshold)
					&& (Robot.intakeRoller.intakeRollerRightCurrent() < currentThreshold)) {
				Robot.intakeRoller.setIntakePower(power, power);
			} else {
				timeInit = Timer.getFPGATimestamp();
				Robot.intakeRoller.setIntakePower(0, 0);
			}
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Robot.vaultBoy.vaultBoyLeftCurrent() > 2) || (Robot.vaultBoy.vaultBoyRightCurrent() > 2));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeRoller.setIntakePower(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}