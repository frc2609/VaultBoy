package org.usfirst.frc.team2609.robot.commands;
import edu.wpi.first.wpilibj.command.Command;

public class LoopCounter extends Command {

	private double loopCount;
	private double loopCountSetpoint;

    public LoopCounter(double loopCountSetpoint){
        this.loopCountSetpoint = loopCountSetpoint;
    }

    protected void initialize() {
    	loopCount = 0;
    }

    protected void execute() {
    	loopCount++;
    }

    protected boolean isFinished() {
    	return (loopCount > loopCountSetpoint);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
