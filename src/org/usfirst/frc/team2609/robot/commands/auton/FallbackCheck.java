package org.usfirst.frc.team2609.robot.commands.auton;

import java.util.Map;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.commands.auton.fallbacks.FallbackSwitchVaultRoutine;

import edu.wpi.first.wpilibj.command.Command;

public class FallbackCheck {
	Map<String, Command> autoMap;
	Map<String, Command> fallbackMap;
	public FallbackCheck(){
		this.autoMap = Robot.autoMap;
		fallbackMap.put("SwitchVaultRoutine", new FallbackSwitchVaultRoutine());
		
	}
	public Command getCheckedAutoCommand(String pickedCommand){
		// Fallbackcheck here
		if(Robot.driveSensors){
			return fallbackMap.get(pickedCommand);
		}else{
			return autoMap.get(pickedCommand);
		}
	}
}
