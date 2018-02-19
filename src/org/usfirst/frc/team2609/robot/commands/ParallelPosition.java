package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ParallelPosition extends CommandGroup {

    public ParallelPosition() {
    	addSequential(new TimerDelay(1.25));
    	addSequential(new SliderPosition(7300));
    }
}
