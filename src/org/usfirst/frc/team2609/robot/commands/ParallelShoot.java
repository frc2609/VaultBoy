package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.commands.intakeRoller.IntakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.VaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ParallelShoot extends CommandGroup {

    public ParallelShoot() {

    	addSequential(new TimerDelay(1.5));
    	addParallel(new VaultBoyPower(1));
    	addParallel(new IntakeRollerPower(0.75,12));
    	addSequential(new TimerDelay(1.5));
    }
}
