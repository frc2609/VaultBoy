package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.Delay;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerBrakeMode;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterPowerCurrentStop;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPowerCurrentStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class SwitchScoreAuto extends CommandGroup {

    public SwitchScoreAuto() {
    	addSequential(new ShooterRoller(0.65));
    	addSequential(new IntakeRollerBrakeMode(false));
    	addParallel(new IntakeRollerPowerNoCurrent(-0.1));
    	addSequential(new VaultBoyPowerCurrentStop(-0.3,3));
    	addSequential(new IntakeRollerBrakeMode(true));
    	addSequential(new VaultBoyPower(0));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
//    	addSequential(new Delay(0.1));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.DOWN));
    	addSequential(new ShooterPowerCurrentStop(0.65,20));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.UP));
    	addSequential(new IntakeRollerPowerNoCurrent(0));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new ShooterRoller(0));
    }
}
