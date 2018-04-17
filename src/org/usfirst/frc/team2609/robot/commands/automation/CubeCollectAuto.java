package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.RumbleOP;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStopAuto;
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
public class CubeCollectAuto extends CommandGroup {

    public CubeCollectAuto() {
    	addSequential(new ShooterRoller(-0.3));
    	addParallel(new VaultBoyPower(0));
    	addParallel(new IntakeRollerPower(0,12));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.UP));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new SliderPosition(7300));
    	//addParallel(new VaultBoyPower(-0.2,10));
    	//addParallel(new VaultBoyPower(.5));
    	addSequential(new IntakeRollerLightSensorStop(0.5,5));
    	addSequential(new ShooterRoller(0));
    	//addParallel(new IntakeRollerPower(-.2,4.5));
    	//addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
//    	addSequential(new SliderPosition(7300));
    	addParallel(new IntakeRollerPower(.25,5));
    	addParallel(new VaultBoyPower(.1));
    	
    	//addParallel(new VaultBoyPower(0.25,10));
    	//addParallel(new IntakeRollerPower(-.2,12));
    	addSequential(new SliderPosition(500));
//    	addParallel(new IntakeRollerPower(0,12));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new VaultBoyPower(0));
    	addSequential(new IntakeRollerPower(0,5));
    	addSequential(new RumbleOP());
    }
}
