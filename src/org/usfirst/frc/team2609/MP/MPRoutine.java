package org.usfirst.frc.team2609.MP;

import org.usfirst.frc.team2609.robot.RobotMap;

import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public abstract class MPRoutine {
	public int length;
	public Waypoint[][] path;
	public boolean[] isReverse;
	public String name;
	public AutoSide side;
	public boolean isPathSet;

	public MPRoutine(int length, AutoSide side) {
		this.length = length;
		this.side = side;
		path = new Waypoint[length][];
		isReverse = new boolean[length];
		if (side == AutoSide.LEFT) {
			RobotMap.plannedPathL = new TankModifier[length];
			RobotMap.isGeneratedL = new boolean[length];
			RobotMap.isDoneMPL = new boolean[length];
		} else if(side == AutoSide.RIGHT){
			RobotMap.plannedPathR = new TankModifier[length];
			RobotMap.isGeneratedR = new boolean[length];
			RobotMap.isDoneMPR = new boolean[length];

		}
	}
	public abstract void setPathsWithOffset(Alliance alliance);

	public void setPath(int id, Waypoint[] path) {
		this.path[id] = path;
		isReverse = new boolean[length];
		if (side == AutoSide.LEFT) {
			RobotMap.plannedPathL = new TankModifier[length];
			RobotMap.isGeneratedL = new boolean[length];
		} else {
			RobotMap.plannedPathR = new TankModifier[length];
			RobotMap.isGeneratedR = new boolean[length];

		}
	}

	public void setReverse(int id, boolean isReverse) {
		this.isReverse[id] = isReverse;
	}

}
