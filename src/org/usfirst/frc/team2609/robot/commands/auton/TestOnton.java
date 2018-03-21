package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.robot.commands.drive.DriveEncoderReset;
import org.usfirst.frc.team2609.robot.commands.drive.DriveGyroReset;
import org.usfirst.frc.team2609.robot.commands.slider.SliderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestOnton extends CommandGroup {

    public TestOnton() {
    	addSequential(new SliderPosition(10000));
    }
}
