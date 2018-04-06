package org.usfirst.frc.team2609.MP;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class PathGenerator {
	Trajectory.Config configFast = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
			Trajectory.Config.SAMPLES_FAST, 0.01, 10, 8.5, 60);
	// TODO: Fix hardcoding of max_vel, max_acc,max_jerk, etc.
	// setProperties(double max_vel, double max_acc, double max_jerk...)
	// perhaps?
	// TODO: Fix hardcoding of Waypoints SetWaypoints(arg0,arg1,arg3...)
	// perhaps?
	// Reid measured hamilton: Rightside: x=87 y=131; Leftside: X=92 Y=131
	// Middle: X=0 Y= 111
	long fastGenTime;
	public String ActiveName;

	public TankModifier trajectoryGenerator(Waypoint[] points) {
		fastGenTime = System.currentTimeMillis();

		Trajectory FastTrajectory = Pathfinder.generate(points, configFast);
		fastGenTime = System.currentTimeMillis() - fastGenTime;
		TankModifier FastModifier = new TankModifier(FastTrajectory).modify(28.75 / 12); // TODO:
																							// Fix
																							// hardcoding
																							// of
																							// wheelbase
																							// width
		System.out.println("generated in:" + fastGenTime);
		return FastModifier;
	}

	public void generate(int id, AutoSide side) {
		if (side == AutoSide.LEFT) {
			RobotMap.plannedPathL[id] = Robot.pathGenerator.trajectoryGenerator(RobotMap.mpRoutineL.path[id]); // Generate
																												// the
																												// path

			System.out.println(RobotMap.plannedPathL[id].getLeftTrajectory().length());
			RobotMap.isGeneratedL[id] = true;
		} else {
			RobotMap.plannedPathR[id] = Robot.pathGenerator.trajectoryGenerator(RobotMap.mpRoutineR.path[id]); // Generate
																												// the
																												// path

			System.out.println(RobotMap.plannedPathR[id].getLeftTrajectory().length());
			RobotMap.isGeneratedR[id] = true;
		}
	}
	public void generateAll() {
		int lengthL = RobotMap.mpRoutineL.path.length - 1;
		int generatedLenL = -1;
		int lengthR = RobotMap.mpRoutineR.path.length - 1;
		int generatedLenR = -1;
		if (ActiveName != RobotMap.mpRoutineL.name && RobotMap.mpRoutineL.isPathSet) {
			if (lengthL > -1) {
				for (int i = 0; i <= lengthL; i++) {
					if(!RobotMap.isGeneratedL.equals(null)){
					if (!RobotMap.isGeneratedL[i]) {
						System.out.println("Generating " + RobotMap.mpRoutineL.name + " section " + i);
						generate(i, AutoSide.LEFT);
						generatedLenL++;
					} else {
						generatedLenL++;

					}
					}
					else{
						System.out.println("Is generated L equals null: " + RobotMap.isGeneratedL.equals(null));
						
					}
				}
			}
			if (lengthR > -1) {
				for (int i = 0; i <= lengthR; i++) {
					if (!RobotMap.isGeneratedR[i]) {
						System.out.println("Generating " + RobotMap.mpRoutineR.name + " section " + i);
						generate(i, AutoSide.RIGHT);
						generatedLenR++;
					} else {
						generatedLenR++;

					}
				}
			}
			if (generatedLenL == lengthL && generatedLenR == lengthR) {
				System.out.println("Generated " + RobotMap.mpRoutineL.name + " and " + RobotMap.mpRoutineR.name);
				ActiveName = RobotMap.mpRoutineL.name;
			}
		}
	}
}