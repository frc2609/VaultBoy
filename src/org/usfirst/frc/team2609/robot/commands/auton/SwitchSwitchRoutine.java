package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.AutoRoutine;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.commands.Delay;
import org.usfirst.frc.team2609.robot.commands.LaunchMP;
import org.usfirst.frc.team2609.robot.commands.SDDelay;
import org.usfirst.frc.team2609.robot.commands.SetMPRoutine;
import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollect;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollectAuto;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollectAuto2;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelPosition;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelPosition500;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelPosition6000;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelShoot;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelShoot2;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelShooterReset;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelSwitchScore;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelSwitchScoreLoadShooter;
import org.usfirst.frc.team2609.robot.commands.automation.ParallelVaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.automation.SwitchScoreAuto;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerLightSensorStop;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPowerNoCurrent;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterPowerCurrentStop;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterRoller;
import org.usfirst.frc.team2609.robot.commands.slider.SliderHome;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPowerSensorStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class SwitchSwitchRoutine extends AutoRoutine {

    public SwitchSwitchRoutine() {
//    	addSequential(new SetMPRoutine(new LeftSwitchSwitchMPRoutine(), new RightSwitchSwitchMPRoutine()));
    	addSequential(new SDDelay());
    	addParallel(new SliderHome());
//    	addParallel(new ShooterRoller(0.65));
//    	addParallel(new ParallelSwitchScore(1.5));
    	addSequential(new LaunchMP(0));
    	addSequential(new SwitchScoreAuto()); // prac
    	// at the switch


    	addParallel(new ParallelShooterReset());
    	addParallel(new ParallelPosition());
    	addSequential(new LaunchMP(1));
    	addSequential(new ShooterRoller(0));
    	// in front of the cube stack
    	
    	addParallel(new IntakeRollerPowerNoCurrent(.5));
//    	addParallel(new CubeCollectAuto());
    	addSequential(new LaunchMP(2));
    	addSequential(new IntakeRollerLightSensorStop(0.45,5)); // jitter // prac
    	addParallel(new IntakeRollerPowerNoCurrent(0.2));
    	addParallel(new VaultBoyPowerSensorStop(.1, false));
    	addSequential(new SliderPosition(3500)); // prac
//    	addParallel(new ParallelPosition500());
    	// picked up cube
    	
    	// PUT PARALELLSHOOT HERE
    	addSequential(new LaunchMP(3));
    	// shot into vault
    	addParallel(new IntakeRollerPowerNoCurrent(0));

    	addParallel(new ParallelPosition500());

//    	addParallel(new ParallelSwitchScoreLoadShooter(1.9)); 
    	addSequential(new LaunchMP(4));
    	addSequential(new SwitchScoreAuto()); // prac

    	addSequential(new LaunchMP(5));
//    	addSequential(new ParallelShooterReset());
//    	addParallel(new IntakeRollerPower(.45,5));
//    	addSequential(new Delay(0.4));
    	// side cube from the stack

    }
    public void forceSetMP(){
    	new SetMPRoutine(new LeftSwitchSwitchMPRoutine(), new RightSwitchSwitchMPRoutine());
    }
}
