package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerVaultBoyStop;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;

/**
 *
 */
public class CubeCollect extends CommandGroup {

    public CubeCollect() {
    	addSequential(new SliderPosition(8900));
    	addParallel(new VaultBoyPower(-0.2,10));
    	addSequential(new IntakeRollerPowerVaultBoyStop(0.75,12));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    	addParallel(new VaultBoyPower(0.25,10));
    	addParallel(new IntakeRollerPower(-.2,12));
    	addSequential(new SliderPosition(500));
    	addParallel(new IntakeRollerPower(0,12));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new VaultBoyPower(0,10));
    }
}
