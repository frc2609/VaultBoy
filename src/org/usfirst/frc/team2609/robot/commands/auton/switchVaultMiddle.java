package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.robot.commands.drive.driveEncoderReset;
import org.usfirst.frc.team2609.robot.commands.drive.driveGyroReset;
import org.usfirst.frc.team2609.robot.commands.drive.driveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class switchVaultMiddle extends CommandGroup {

    public switchVaultMiddle() {
    	addSequential(new driveGyroReset());
    	addSequential(new driveEncoderReset());
    	addSequential(new driveStraight(10,0));
    }
}
