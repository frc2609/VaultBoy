package org.usfirst.frc.team2609.robot.commands.auton;

import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.MPRoutine;
import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class RightSwitchSwitchMPRoutine extends MPRoutine {
	static final int length = 6; // 0,1,2
	static final AutoSide side = AutoSide.RIGHT;
	Waypoint[] MiddleToSwitch = new Waypoint[]{
			new Waypoint(0,0,Pathfinder.d2r(0)),
			new Waypoint(9, -3.25, Pathfinder.d2r(0))};
	Waypoint[] SwitchToStackMiddle = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(6.5, 4, Pathfinder.d2r(0))};
	Waypoint[] StackMiddleToCubeMiddle = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(3.75, 0, Pathfinder.d2r(0))};
	Waypoint[] CubeMiddleToIntermediate = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(3.75, -2.5, Pathfinder.d2r(0))};
	Waypoint[] IntermediateToSwitch = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(7, -1, Pathfinder.d2r(0))};
	Waypoint[] SwitchTo2ndIntermediate = new Waypoint[]{
			new Waypoint(0,	0,Pathfinder.d2r(0)),
			new Waypoint(5, -3, Pathfinder.d2r(0))};
	
	
	public RightSwitchSwitchMPRoutine() {
		super(length,side);
		
		//register trigger for autoside and .setPath
		
	}
	@Override
	public void setPathsWithOffset(Alliance alliance){
		super.setPath(0, MiddleToSwitch);
		super.setPath(1, SwitchToStackMiddle);
		super.setPath(2, StackMiddleToCubeMiddle);
		super.setPath(3, CubeMiddleToIntermediate);
		super.setPath(4, IntermediateToSwitch);
		super.setPath(5, SwitchTo2ndIntermediate);
		
		
		if(alliance == Alliance.Blue){
			// set blue offsets here

			super.name = "Right Switch Switch Blue";
			System.out.println("BLUE OFFSETS");
		}else if(alliance == Alliance.Red){
			// set red offsets here
			System.out.println("RED OFFSETS");

			super.name = "Right Switch Switch Red";
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
