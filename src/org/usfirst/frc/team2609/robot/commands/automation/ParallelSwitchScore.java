package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class ParallelSwitchScore extends CommandGroup {

    public ParallelSwitchScore(double delay) {
    	addSequential(new TimerDelay(delay));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.DOWN));
    	addSequential(new TimerDelay(0.2));
//    	addParallel(new IntakeRollerPower(0.45,5));
//    	addParallel(new IntakeRollerPowerNoCurrent(0.75));
    	addParallel(new ShooterRoller(0.4));
    }
}
