package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.commands.Delay;
import org.usfirst.frc.team2609.robot.commands.LaunchMP;
import org.usfirst.frc.team2609.robot.commands.SDDelay;
import org.usfirst.frc.team2609.robot.commands.SetMPRoutine;
import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollect;
import org.usfirst.frc.team2609.robot.commands.automation.SwitchScore;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.slider.SliderHome;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class SwitchVaultRoutine extends CommandGroup {

    public SwitchVaultRoutine() {
    	addSequential(new SetMPRoutine(new LeftSwitchVaultMPRoutine(), new RightSwitchVaultMPRoutine()));
    	addSequential(new SDDelay());
    	addParallel(new SliderHome());
    	addParallel(new ShooterRoller(1));
    	addSequential(new Delay(0.1));
    	addSequential(new LaunchMP(0));	
    	addSequential(new SwitchScore());	
    	//    	addSequential(new SwitchScore());
//    	addSequential(new Delay(2));
    	addSequential(new Delay(0.1));
    	addSequential(new LaunchMP(1));
    	addParallel(new CubeCollect());
//    	addSequential(new Delay(2));
    	addSequential(new Delay(0.1));
    	addSequential(new LaunchMP(2));
//    	addSequential(new Delay(2));
    	addSequential(new Delay(0.1));
    	addSequential(new LaunchMP(3));
    	addParallel(new VaultBoyPower(1));
    	addParallel(new IntakeRollerPower(0.75,12));
    	addSequential(new Delay(1));
    	addParallel(new CubeCollect());
    	addSequential(new Delay(0.1));
    	addSequential(new LaunchMP(4));
//    	addSequential(new Delay(2));
    	addSequential(new Delay(0.1));
    	addSequential(new LaunchMP(5));
    	addParallel(new VaultBoyPower(1));
    	addParallel(new IntakeRollerPower(0.75,12));
    }
}
