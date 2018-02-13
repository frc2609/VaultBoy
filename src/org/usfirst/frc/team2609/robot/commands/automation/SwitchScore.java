package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class SwitchScore extends CommandGroup {

    public SwitchScore() {
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    	addParallel(new ShooterRoller(1));
    	addSequential(new TimerDelay(0.5));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.IN));
    	addSequential(new TimerDelay(1.5));
    	addSequential(new ShooterRoller(0));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.OUT));
    	addSequential(new TimerDelay(0.5));
//    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    }
}
