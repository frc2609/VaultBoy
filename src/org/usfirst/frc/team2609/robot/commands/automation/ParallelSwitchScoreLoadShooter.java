package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerBrakeMode;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
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
public class ParallelSwitchScoreLoadShooter extends CommandGroup {

    public ParallelSwitchScoreLoadShooter(double delay) {
    	addSequential(new TimerDelay(delay));
    	addSequential(new IntakeRollerBrakeMode(false));
    	addParallel(new IntakeRollerPowerNoCurrent(-0.1));
    	addSequential(new VaultBoyPowerCurrentStop(-0.3,3));
    	addSequential(new IntakeRollerBrakeMode(true));
    	addSequential(new VaultBoyPower(0));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.DOWN));
    }
}
