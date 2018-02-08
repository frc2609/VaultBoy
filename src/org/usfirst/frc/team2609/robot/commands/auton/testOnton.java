package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.robot.commands.drive.driveEncoderReset;
import org.usfirst.frc.team2609.robot.commands.drive.driveGyroReset;
import org.usfirst.frc.team2609.robot.commands.drive.driveStraight;
import org.usfirst.frc.team2609.robot.commands.slider.sliderPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class testOnton extends CommandGroup {

    public testOnton() {
    	addSequential(new sliderPosition(10000));
    }
}
