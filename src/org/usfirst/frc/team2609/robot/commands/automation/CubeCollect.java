package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.RumbleOP;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.CubePincherSetState;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerBrakeMode;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStopNoCurrent;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerVaultBoyStop;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPowerSensorStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class CubeCollect extends CommandGroup {


    public CubeCollect() {
    	addSequential(new IntakeRollerBrakeMode(true));
    	addSequential(new ShooterRoller(-0.3));
    	addParallel(new VaultBoyPower(0));
    	addSequential(new ShooterActivatorSetState(ShooterActivatorState.UP));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new CubePincherSetState(IntakeActivatorState.OUT));	//pincher out
    	addParallel(new IntakeRollerPower(0.45,5));
    	addParallel(new VaultBoyPowerSensorStop(.1, false));
    	addSequential(new SliderPosition(7300));
    	//addParallel(new VaultBoyPower(-0.2,10));
    	//addParallel(new VaultBoyPower(.5));
//    	addSequential(new IntakeRollerPowerNoCurrent(.65));
    	addSequential(new IntakeRollerLightSensorStop(0.45,5));
    	addParallel(new IntakeRollerPowerNoCurrent(0.2));
    	addParallel(new VaultBoyPowerSensorStop(.1, false));
    	addSequential(new SliderPosition(3500));
    	addSequential(new ShooterRoller(0));
//    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    	addParallel(new IntakeRollerPowerNoCurrent(-0.05));
    	addSequential(new SliderPosition(500));
    	//addParallel(new IntakeRollerPower(0,12));
//    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.IN));
    	addSequential(new CubePincherSetState(IntakeActivatorState.OUT));	//pincher out
    	addSequential(new IntakeRollerBrakeMode(true));
    	addSequential(new VaultBoyPower(0));
    	addSequential(new IntakeRollerPowerNoCurrent(0));
    	addSequential(new RumbleOP());
    }
}
