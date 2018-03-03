package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class RightSwitchVaultMPRoutine extends MPRoutine {
	static final int length = 6; // 0,1,2
	static final AutoSide side = AutoSide.RIGHT;
	public RightSwitchVaultMPRoutine() {
		super(length,side);
		super.setPath(0, new Waypoint[]{
				new Waypoint(0,0,Pathfinder.d2r(0)),
				new Waypoint(9, -3.75, Pathfinder.d2r(0))});
		super.setPath(1, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(6.5, 4.25, Pathfinder.d2r(0))});
		super.setPath(2, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(3.75, 0, Pathfinder.d2r(0))});
		super.setPath(3, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(7, 2.1, Pathfinder.d2r(0))});
		super.setPath(4, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(7, -1, -Pathfinder.d2r(30))});
		super.setPath(5, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(30)),
				new Waypoint(7, 0.9, Pathfinder.d2r(0))});
		super.setReverse(0, false);
		super.setReverse(1, true);
		super.setReverse(2, false);
		super.setReverse(3, true);
		super.setReverse(4, false);
		super.setReverse(5, true);
		
		super.name = "Right Switch Vault";
	}

}
