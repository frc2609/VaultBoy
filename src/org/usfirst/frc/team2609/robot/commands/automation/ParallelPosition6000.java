package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ParallelPosition6000 extends CommandGroup {

    public ParallelPosition6000() {
    	addSequential(new TimerDelay(1.25));
    	addSequential(new SliderPosition(6000));
    }
}
