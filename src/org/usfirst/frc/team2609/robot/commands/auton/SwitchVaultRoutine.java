package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.commands.LaunchMP;
import org.usfirst.frc.team2609.robot.commands.SetMPRoutine;
import org.usfirst.frc.team2609.robot.commands.automation.CubeCollect;
import org.usfirst.frc.team2609.robot.commands.slider.SliderHome;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchVaultRoutine extends CommandGroup {

    public SwitchVaultRoutine(MPRoutine routine) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

    	addSequential(new SetMPRoutine(routine));
    	addParallel(new SliderHome());
    	addSequential(new LaunchMP(0));    	
//    	addSequential(new Delay(2));
    	addSequential(new LaunchMP(1));
    	addSequential(new CubeCollect());
//    	addSequential(new Delay(2));
    	addSequential(new LaunchMP(2));
//    	addSequential(new Delay(2));
    	addSequential(new LaunchMP(3));
    	addSequential(new LaunchMP(4));
//    	addSequential(new Delay(2));
    	addSequential(new LaunchMP(5));
    }
}
