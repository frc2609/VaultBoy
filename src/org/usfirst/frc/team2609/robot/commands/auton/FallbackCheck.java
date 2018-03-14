package org.usfirst.frc.team2609.robot.commands.auton;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.commands.auton.fallbacks.FallbackSwitchVaultRoutine;

import edu.wpi.first.wpilibj.command.Command;

public class FallbackCheck {
	public FallbackCheck(){
		
	}
	public Command getCheckedAutoCommand(String pickedCommand){
		// Fallbackcheck here
		if(Robot.driveSensors){
			return Robot.fallbackMap.get(pickedCommand);
		}else{
			return Robot.autoMap.get(pickedCommand);
		}
	}
}
