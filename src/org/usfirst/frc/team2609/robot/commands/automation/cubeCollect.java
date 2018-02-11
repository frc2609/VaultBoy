package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.intakeActivator.intakeActivatorState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.intakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.intakeRollerPowerVaultBoyStop;
import org.usfirst.frc.team2609.robot.commands.slider.sliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.vaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;

/**
 *
 */
public class cubeCollect extends CommandGroup {

    public cubeCollect() {
    	addSequential(new sliderPosition(6200));
    	addParallel(new vaultBoyPower(-0.2,10));
    	addSequential(new intakeRollerPowerVaultBoyStop(0.75,12));
    	addSequential(new intakeActivatorState(IntakeActivatorState.OUT));
    	addParallel(new vaultBoyPower(0.25,10));
    	addParallel(new intakeRollerPower(-.2,12));
    	addSequential(new sliderPosition(500));
    	addParallel(new intakeRollerPower(0,12));
    	addSequential(new intakeActivatorState(IntakeActivatorState.IN));
    	addSequential(new vaultBoyPower(0,10));
    }
}
