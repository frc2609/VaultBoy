package org.usfirst.frc.team2609.robot.commands.automation;

import org.usfirst.frc.team2609.robot.commands.intakeActivator.intakeActivatorState;
import org.usfirst.frc.team2609.robot.commands.intakeRoller.intakeRollerPower;
import org.usfirst.frc.team2609.robot.commands.slider.sliderPosition;
import org.usfirst.frc.team2609.robot.commands.vaultBoy.vaultBoyPower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.IntakeActivatorState;

/**
 *
 */
public class cubeCollect extends CommandGroup {

    public cubeCollect() {
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
    	addSequential(new sliderPosition(10000));
    	addSequential(new intakeActivatorState(IntakeActivatorState.OUT));
    	addParallel(new vaultBoyPower(0.2,10));
    	addSequential(new intakeRollerPower(0.7,10));
    	addSequential(new intakeActivatorState(IntakeActivatorState.IN));
    	addSequential(new sliderPosition(100));
    	
    	
    }
}
