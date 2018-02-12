package org.usfirst.frc.team2609.MP;

import org.usfirst.frc.team2609.robot.RobotMap;

import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class MPRoutine {
	public int length;
	public Waypoint[][] path; 
	public boolean[] isReverse;
	public MPRoutine(int length){
		this.length = length;
		path = new Waypoint[length][];
		isReverse = new boolean[length];
		RobotMap.plannedPath = new TankModifier[length];
		RobotMap.isGenerated = new boolean[length];
		RobotMap.isDoneMP = new boolean[length];
	}
	public void setPath(int id, Waypoint[] path){
		this.path[id] = path;
		isReverse = new boolean[length];
		RobotMap.plannedPath = new TankModifier[length];
		RobotMap.isGenerated = new boolean[length];
	}
	public void setReverse(int id, boolean isReverse){
		this.isReverse[id] = isReverse;
	}
	
}
