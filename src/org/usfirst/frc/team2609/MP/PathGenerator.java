package org.usfirst.frc.team2609.MP;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class PathGenerator {
	Trajectory.Config configFast = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, 0.01, 7, 4, 60); 
	// TODO: Fix hardcoding of max_vel, max_acc,max_jerk, etc. setProperties(double max_vel, double max_acc, double max_jerk...) perhaps?
	public Waypoint[] FiveFeetForwardPoints = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(5, 0, Pathfinder.d2r(0))
	};
	public Waypoint[] TenFeetForwardPoints = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(14, 0, Pathfinder.d2r(0))
	};
	public Waypoint[] TheoreticalGearSwerve = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(-105*0.08333333333, -70*0.08333333333, Pathfinder.d2r(60)) // in front of the peg
	};
	public Waypoint[] TheoreticalGearSwerve2 = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
            new Waypoint(111/12, -82/12, Pathfinder.d2r(-60)) // HITTING THE WALL IN HAMILTON
	};
	public Waypoint[] TheoreticalGearSwervePractice = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
//            new Waypoint(133/12, 45/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
//            new Waypoint(108/12, 59/12, Pathfinder.d2r(60)) // 2.5fps 3fps2
//            new Waypoint(107/12, 57.5/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
            new Waypoint(9, -9, Pathfinder.d2r(-60)) // HITTING THE WALL IN HAMILTON
	};
	public Waypoint[] CustomSwerve = new Waypoint[]{
            new Waypoint(0, 0, Pathfinder.d2r(0)),
//            new Waypoint(133/12, 45/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
//            new Waypoint(108/12, 59/12, Pathfinder.d2r(60)) // 2.5fps 3fps2
//            new Waypoint(107/12, 57.5/12, Pathfinder.d2r(60)) // HITTING THE WALL IN HAMILTON
            new Waypoint(7, -4, Pathfinder.d2r(-60)), // HITTING THE WALL IN HAMILTON
            new Waypoint(12, -8, Pathfinder.d2r(0)) // HITTING THE WALL IN HAMILTON
	};
	public Waypoint[] SwitchSwerveRight = new Waypoint[]{
	          new Waypoint(0, 0, Pathfinder.d2r(0)),
	          new Waypoint(9, -4, Pathfinder.d2r(0)) // HITTING THE WALL IN HAMILTON
		};
	public Waypoint[] SwitchSwerveLeft = new Waypoint[]{
		          new Waypoint(0, 0, Pathfinder.d2r(0)),
		          new Waypoint(9, 5, Pathfinder.d2r(0)) // HITTING THE WALL IN HAMILTON
	};
	// TODO: Fix hardcoding of Waypoints SetWaypoints(arg0,arg1,arg3...) perhaps?
//  Reid measured hamilton: Rightside: x=87 y=131; Leftside: X=92 Y=131 Middle: X=0 Y= 111
    long fastGenTime;
	public TankModifier trajectoryGenerator(Waypoint[] points){
		fastGenTime = System.currentTimeMillis();
		
        Trajectory FastTrajectory = Pathfinder.generate(points, configFast);
        fastGenTime = System.currentTimeMillis() - fastGenTime;
        TankModifier FastModifier = new TankModifier(FastTrajectory).modify(28.75/12); // TODO: Fix hardcoding of wheelbase width
        System.out.println("generated in:" + fastGenTime);
        return FastModifier;
	}
}
