package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.RumbleOP;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStopNoCurrent;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerNoLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerVaultBoyStop;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class LayEgg extends CommandGroup {


    public LayEgg() {
    	addSequential(new ShooterRoller(-0.3));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.UP));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addParallel(new VaultBoyPower(-0.2));
    	addParallel(new IntakeRollerPowerNoCurrent(-0.2));
    	addParallel(new SliderPosition(500));
    	addSequential(new IntakeRollerNoLightSensorStop(-0.3));
    	addParallel(new ShooterRoller(0));
    	addParallel(new IntakeRollerPowerNoCurrent(0));
    	addParallel(new VaultBoyPower(0));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    }
}
