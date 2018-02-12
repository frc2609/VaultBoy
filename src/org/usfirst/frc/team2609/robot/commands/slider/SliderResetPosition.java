package org.usfirst.frc.team2609.robot.commands.slider;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SliderResetPosition extends CommandGroup {

    public SliderResetPosition() {
    	addSequential(new SliderPower(-0.15,2));
    	addSequential(new SliderEncoderReset());
    	addSequential(new SliderPosition(500));
    }
}