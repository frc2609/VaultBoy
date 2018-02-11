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
    	addSequential(new sliderPosition(7000));
    	addSequential(new intakeActivatorState(IntakeActivatorState.OUT));
    	addParallel(new vaultBoyPower(0.2,10));
    	addSequential(new intakeRollerPower(0.7,10));
    	addSequential(new intakeActivatorState(IntakeActivatorState.IN));
    	addSequential(new sliderPosition(200));
    }
}
