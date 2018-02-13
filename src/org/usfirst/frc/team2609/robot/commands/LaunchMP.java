package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;


import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 *
 */
public class LaunchMP extends Command {

	EncoderFollower left = Robot.drivetrain.left;
	EncoderFollower right = Robot.drivetrain.right;
	int id = 0;
    public LaunchMP() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public LaunchMP(int id){
    	this.id = id;
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	Robot.isDriveTrainMPActive = false;
//        RobotMap.ahrs.zeroYaw();
    	Robot.pathGenerator.generate(id);
        System.out.println("Starting init LaunchMP");
        try{
        	Robot.drivetrain.initMP(id);
        }catch(Throwable t){
        	System.out.println("LaunchMP error");
        	System.out.println(t.getMessage());
        	System.out.println(t.getStackTrace());
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(RobotMap.generatedEh){
//    		double leftOutput = left.calculate(RobotMap.left1.getEncPosition());
//    		double rightOutput = right.calculate(RobotMap.right1.getEncPosition());
//    		if(leftOutput != 0 && rightOutput!=0){
//    			Trajectory.Segment rightSegment = right.getSegment();
//    			Trajectory.Segment leftSegment = left.getSegment();
//    			Robot.logger.logMP(leftSegment, rightSegment);
//    		}
//    		RobotMap.left1.set(leftOutput);
//    		RobotMap.right1.set(rightOutput);
//    	}
    	// This timing of this loop runs anywhere between 100hz and 7hz. DO NOT USE
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.isDoneMP[id];
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}