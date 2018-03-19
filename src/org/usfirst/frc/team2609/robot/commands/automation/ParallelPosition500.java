package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ParallelPosition500 extends CommandGroup {

    public ParallelPosition500() {
    	addSequential(new TimerDelay(1));
    	addSequential(new SliderPosition(500));
    }
}
