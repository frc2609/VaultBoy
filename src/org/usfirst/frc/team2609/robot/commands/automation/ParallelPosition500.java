package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.intakeActivator.IntakeActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.shooter.ShooterActivatorSetState;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;
import enums.ShooterActivatorState;

/**
 *
 */
public class ParallelPosition500 extends CommandGroup {

    public ParallelPosition500() {
//    	addSequential(new TimerDelay(1));
    	addSequential(new IntakeActivatorSetState(IntakeActivatorState.OUT));
    	addSequential(new SliderPosition(500));
    }
}
