package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.Delay;
import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerBrakeMode;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPowerCurrentStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class SwitchScore extends CommandGroup {

    public SwitchScore() {
    	addSequential(new ShooterRoller(1));
    	addSequential(new IntakeRollerBrakeMode(false));
    	addSequential(new VaultBoyPowerCurrentStop(-0.3,3));
    	addSequential(new IntakeRollerBrakeMode(true));
    	addSequential(new VaultBoyPower(0));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    	addSequential(new Delay(0.1));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.DOWN));
    	addSequential(new Delay(1));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.UP));
    	addSequential(new Delay(0.1));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new ShooterRoller(0));
    }
}
