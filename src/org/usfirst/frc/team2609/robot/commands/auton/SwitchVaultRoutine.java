package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.commands.Delay;
import org.usfirst.frc.team2609.robot.commands.LaunchMP;
import org.usfirst.frc.team2609.robot.commands.ParallelPosition;
import org.usfirst.frc.team2609.robot.commands.ParallelShoot;
import org.usfirst.frc.team2609.robot.commands.ParallelShoot2;
import org.usfirst.frc.team2609.robot.commands.ParallelVaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.SDDelay;
import org.usfirst.frc.team2609.robot.commands.SetMPRoutine;
import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollect;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollectAuto;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollectAuto2;
import org.usfirst.frc.team2609.robot.commands.automation.SwitchScore;
import org.usfirst.frc.team2609.robot.commands.automation.SwitchScoreAuto;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.slider.SliderHome;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;
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
    	addSequential(new LaunchMP(0));
    	// at the switch
    	
    	addSequential(new SwitchScoreAuto());
    	addParallel(new ParallelPosition());
    	addSequential(new LaunchMP(1));
    	// in front of the cube stack
    	
    	addParallel(new IntakeRollerPowerNoCurrent(.5));
//    	addParallel(new CubeCollectAuto());
    	addSequential(new LaunchMP(2));
    	// picked up cube
    	
    	// PUT PARALELLSHOOT HERE
    	addParallel(new ParallelShoot());
    	addSequential(new LaunchMP(3));
    	// shot into vault
    	

    	addParallel(new ParallelVaultBoyPower());
    	addParallel(new IntakeRollerPower(0.45,5));
    	addSequential(new LaunchMP(4));
//    	addParallel(new IntakeRollerPower(.45,5));
//    	addSequential(new Delay(0.4));
    	// side cube from the stack
    	
    	addParallel(new IntakeRollerPower(.45,5));
    	addParallel(new ParallelShoot2());
    	addSequential(new LaunchMP(5));
    	// at the vault
    }
    public void forceSetMP(){
    	new SetMPRoutine(new LeftSwitchVaultMPRoutine(), new RightSwitchVaultMPRoutine());
    }
}
