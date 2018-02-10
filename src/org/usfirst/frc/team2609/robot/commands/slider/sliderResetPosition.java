package org.usfirst.frc.team2609.robot.commands.slider;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class sliderResetPosition extends CommandGroup {

    public sliderResetPosition() {
    	addSequential(new sliderPower(-0.2,5));
    	addSequential(new sliderEncoderReset());
    }
}