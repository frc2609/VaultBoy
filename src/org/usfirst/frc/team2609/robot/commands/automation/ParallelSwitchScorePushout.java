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
public class ParallelSwitchScorePushout extends CommandGroup {

    public ParallelSwitchScorePushout(double delay) {
    	addSequential(new TimerDelay(delay));
    	addParallel(new ShooterRoller(0.7));
    	addSequential(new VaultBoyPower(-0.3));
    	addSequential(new TimerDelay(0.1));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.DOWN));
    }
}
