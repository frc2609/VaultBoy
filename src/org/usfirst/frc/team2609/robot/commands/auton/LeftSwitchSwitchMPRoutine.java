package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class LeftSwitchSwitchMPRoutine extends MPRoutine {
	static final int length = 6; // 0,1,2
	static final AutoSide side = AutoSide.LEFT;

	Waypoint[] MiddleToSwitch = new Waypoint[]{
			new Waypoint(0,0,Pathfinder.d2r(0)),
			new Waypoint(9.25, 5, Pathfinder.d2r(0))};//Changed from 9 to 9.25 to get centre cube
	Waypoint[] SwitchToStackMiddle = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(5.75, -4, Pathfinder.d2r(0))};
	Waypoint[] StackMiddleToCubeMiddle = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(2.75, 0, Pathfinder.d2r(0))};
	Waypoint[] StackMiddleToSwitch = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(6.5, 4.25, Pathfinder.d2r(0))};
	Waypoint[] SwitchTo2ndIntermediate = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(5, 0, Pathfinder.d2r(0))};//Changed from 5.5 to 5 to try and get the 2nd cube 
	Waypoint[] SecondIntermediateTo2ndCube = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(3.25, -2.25, -Pathfinder.d2r(30))};
	Waypoint[] SwitchToRunSetup = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(4, 4, Pathfinder.d2r(0))};
	
	public LeftSwitchSwitchMPRoutine() {
		super(length, side);
		
		
	}
	
	@Override
	public void setPathsWithOffset(Alliance alliance){
		super.setPath(0, MiddleToSwitch);
		super.setPath(1, SwitchToStackMiddle);
		super.setPath(2, StackMiddleToCubeMiddle);
		super.setPath(3, StackMiddleToCubeMiddle);
		super.setPath(4, StackMiddleToSwitch);
		super.setPath(5, SwitchToRunSetup);
		
		if(alliance == Alliance.Blue){
			// set blue offsets here

			super.name = "Left Switch Switch Blue";
			System.out.println("BLUE OFFSETS");
		}else if(alliance == Alliance.Red){
			// set red offsets here
			System.out.println("RED OFFSETS");
			super.setPath(1, new Waypoint[]{
					new Waypoint(0,	0,Pathfinder.d2r(0)),
					new Waypoint(5.75, -4.25, Pathfinder.d2r(0))});

			super.name = "Left Switch Switch Red";
		}else{
			DriverStation.reportError("INVALID OFFSETS", false);
		}
		
		

		super.setReverse(0, false);
		super.setReverse(1, true);
		super.setReverse(2, false);
		super.setReverse(3, true);
		super.setReverse(4, false);
		super.setReverse(5, true);
		
		super.isPathSet = true;
	}

}
/*
package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class LeftSwitchVaultMPRoutine extends MPRoutine {
	static final int length = 6; // 0,1,2
	static final AutoSide side = AutoSide.LEFT;
	public LeftSwitchVaultMPRoutine() {
		super(length, side);
		super.setPath(0, new Waypoint[]{
				new Waypoint(0,0,Pathfinder.d2r(0)),
				new Waypoint(9, 5, Pathfinder.d2r(0))});
		super.setPath(1, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(7, -5, Pathfinder.d2r(0))});
		super.setPath(2, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(3.75, 0, Pathfinder.d2r(0))});
		super.setPath(3, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(6.25, 3.5, Pathfinder.d2r(0))});
		super.setPath(4, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(0)),
				new Waypoint(7.4, -1.75, -Pathfinder.d2r(30))});
		super.setPath(5, new Waypoint[]{
				new Waypoint(0,	0,Pathfinder.d2r(30)),
				new Waypoint(7, 1.9, Pathfinder.d2r(0))});
		super.setReverse(0, false);
		super.setReverse(1, true);
		super.setReverse(2, false);
		super.setReverse(3, true);
		super.setReverse(5, true);
		

		super.name = "Left Switch Vault";
	}

}
*/