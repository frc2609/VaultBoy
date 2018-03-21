package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.robot.commands.drive.DriveEncoderReset;
import org.usfirst.frc.team2609.robot.commands.drive.DriveGyroReset;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchVaultMiddle extends CommandGroup {

    public SwitchVaultMiddle() {
    	addSequential(new DriveGyroReset());
    	addSequential(new DriveEncoderReset());
    }
}
