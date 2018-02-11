package org.usfirst.frc.team2609.robot.commands.slider;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class sliderResetPosition extends CommandGroup {

    public sliderResetPosition() {
    	addSequential(new sliderPower(-0.15,2));
    	addSequential(new sliderEncoderReset());
    	addSequential(new sliderPosition(500));
    }
}