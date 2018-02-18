package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.MP.AutoSide;
import org.usfirst.frc.team2609.MP.Loop;
import org.usfirst.frc.team2609.MP.MPConstants;
import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.DriveState;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private int currID;
	private double timeStarted;
	private static boolean reverse;
	private double last_world_linear_accel_x,last_world_linear_accel_y;
	public EncoderFollower left,right;
	
	
	
	private final org.usfirst.frc.team2609.MP.Loop mLoop = new Loop() {
		@Override
		public void onStart(){
			System.out.println("Starting DriveTrain loop");
		}
		@Override
		public void onLoop(){
			synchronized (Drivetrain.this){
//				Robot.logger.logAll();
				if(Robot.isDriveTrainMPActive){
					double timeSinceStarted = Timer.getFPGATimestamp() - timeStarted;
//					System.out.println(timeSinceStarted);
					// Run motion profiler
					if (!reverse) { //forward
						double leftOutput = left.calculate(-getLeftQuad(), timeSinceStarted);
						double rightOutput = right.calculate(getRightQuad(), timeSinceStarted);

						double angleError = (Pathfinder.boundHalfDegrees(Pathfinder.r2d(left.getHeading()))
								- (-RobotMap.ahrs.getYaw())); // gyro correction

						double turn = 0.012 * Pathfinder.boundHalfDegrees(angleError); // P = 0.012
						if (turn > 0) {
							turn = Math.min(turn, 0.2);
						} else if (turn < 0) {
							turn = Math.max(turn, -0.2);
						} else {
							turn = 0;
						}
						leftOutput = leftOutput - turn;
						rightOutput = rightOutput + turn;
						
						setDrive(DriveState.AUTON, leftOutput, rightOutput);
//						System.out.println(leftOutput);
					} 
					else {
						//reverse
						double leftOutput = left.calculate(getLeftQuad(),
								timeSinceStarted);
						double rightOutput = right.calculate(-getRightQuad(),
								timeSinceStarted);

						double angleError = (Pathfinder.boundHalfDegrees(Pathfinder.r2d(left.getHeading()))
								- (RobotMap.ahrs.getYaw()));

						double turn = 0.012 * Pathfinder.boundHalfDegrees(angleError);
						if (turn > 0) {
							turn = Math.min(turn, 0.2);
						} else if (turn < 0) {
							turn = Math.max(turn, -0.2);
						} else {
							turn = 0;
						}
						leftOutput = leftOutput - turn;
						rightOutput = rightOutput + turn;

						setDrive(DriveState.AUTON, -leftOutput, -rightOutput);

					}
					
					
					//isFinished logic
					if (left.isFinished(Timer.getFPGATimestamp(), timeStarted)
							&& right.isFinished(Timer.getFPGATimestamp(), timeStarted)) {
						Robot.isDriveTrainMPActive = false;
						if(RobotMap.activeSide == AutoSide.LEFT){
							RobotMap.isDoneMPL[currID] = true;
						}else{
							RobotMap.isDoneMPR[currID] = true;
						}
//						RobotMap.isDoneMP[currID] = true;
						setDrive(DriveState.AUTON, 0, 0);
						System.out.println("Both trajectories finished");
					} else {
						double[] leftInterpolated = left.getInterpolatedSegment(timeSinceStarted);
						double[] rightInterpolated = right.getInterpolatedSegment(timeSinceStarted);
						Robot.logger.logInterpolatedMP(leftInterpolated, rightInterpolated);
					}
			    	
				}
				else{
//					Robot.logger.logAll();
				}
			}
		}
		@Override
		public void onStop(){
			System.out.println("Ending DriveTrain loop");
		}
	};
	
	public Loop getLooper(){
		return mLoop;
	}
	
	public void setDrive(DriveState desiredState,double leftPower, double rightPower){
		switch(desiredState){
		case TELEOP:
	        RobotMap.driveLeft1.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,rightPower);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,rightPower);
			break;
		case AUTON:
			RobotMap.driveLeft1.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,leftPower);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,rightPower);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,rightPower);
	        // TODO: disable arcade drive?
			break;
		case DISABLE:
	        RobotMap.driveLeft1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,0);
			break;
		default:
	        RobotMap.driveLeft1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveLeft2.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight1.set(ControlMode.PercentOutput,0);
	        RobotMap.driveRight2.set(ControlMode.PercentOutput,0);
			break;
		}
	}
	
    public void resetEncoders(){
    
    	RobotMap.driveLeft1.getSensorCollection().setQuadraturePosition(0,0);
    	RobotMap.driveRight1.getSensorCollection().setQuadraturePosition(0,0);
    }

    public void resetGyro(){
    	RobotMap.ahrs.zeroYaw();
    }
    
    public double getLeftEncoderInches(){
		return RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition() * Robot.ticksToInches;
    }
    
    public double getRightEncoderInches(){
		return RobotMap.driveRight2.getSensorCollection().getQuadraturePosition() * Robot.ticksToInches;
    }
    
    public double getInverseLeftEncoderInches(){
		return -RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition() * Robot.ticksToInches;
    }
    
    public double getInverseRightEncoderInches(){
		return -RobotMap.driveRight2.getSensorCollection().getQuadraturePosition() * Robot.ticksToInches;
    }
    public int getRightQuad(){
    	return RobotMap.driveRight1.getSensorCollection().getQuadraturePosition();
    }
    
    public int getLeftQuad(){
    	return RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition();
    }
    public double getRightFeet(){
    	return RobotMap.driveRight1.getPosition();
    }
    
    public double getLeftFeet(){
    	return RobotMap.driveLeft1.getPosition();
    }
    
    public double nativeVelToFPSL(double nativeVel){
    	return -((nativeVel*10)/4)/MPConstants.leftEncPerFeet;
    }
    public double nativeVelToFPSR(double nativeVel){
    	return ((nativeVel*10)/4)/MPConstants.rightEncPerFeet;
    }
    public void changeBreakMode(boolean flip){
    	NeutralMode mode = (flip) ? NeutralMode.Brake : NeutralMode.Coast; // if flip -> Brake, if !flip -> Coast
		RobotMap.driveLeft1.setNeutralMode(mode);
		RobotMap.driveLeft2.setNeutralMode(mode);
		RobotMap.driveRight1.setNeutralMode(mode);
		RobotMap.driveRight2.setNeutralMode(mode);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void initMP(int id){
		this.currID = id;
		boolean isGeneratedCurr,isDoneCurr,isReverseCurr;
		MPConstants.sdGet();
		if (RobotMap.activeSide == AutoSide.LEFT) {
			isDoneCurr = RobotMap.isDoneMPL[currID];
			isGeneratedCurr = RobotMap.isGeneratedL[id];
			isReverseCurr = RobotMap.mpRoutineL.isReverse[id];
			left = new EncoderFollower(RobotMap.plannedPathL[id].getLeftTrajectory());
			right = new EncoderFollower(RobotMap.plannedPathL[id].getRightTrajectory());
			this.reverse = isReverseCurr;
		} else {
			isDoneCurr = RobotMap.isDoneMPR[currID];
			isGeneratedCurr = RobotMap.isGeneratedR[id];
			isReverseCurr = RobotMap.mpRoutineR.isReverse[id];
			left = new EncoderFollower(RobotMap.plannedPathR[id].getLeftTrajectory());
			right = new EncoderFollower(RobotMap.plannedPathR[id].getRightTrajectory());
			this.reverse = isReverseCurr;
		}
		
		if (isGeneratedCurr && !isReverseCurr) { // The point of this is to make sure that the trajectory has been generated.
			
			this.resetEncoders();
			left.configureEncoder(RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition(), 4096, 0.5); // 4096 magenc
			left.configurePIDVA(MPConstants.kP, MPConstants.kI, MPConstants.kD, 1 / MPConstants.kV, MPConstants.kA);

//			left.configurePIDVA(0, 0, 0, 1 / 10, 0);

			right.configureEncoder(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition(), 4096, 0.5); //4096
			right.configurePIDVA(MPConstants.kP, MPConstants.kI, MPConstants.kD, 1 / MPConstants.kV, MPConstants.kA);

//			right.configurePIDVA(0, 0, 0, 1 / 10, 0);

			Robot.isDriveTrainMPActive = true;
			this.timeStarted = Timer.getFPGATimestamp();
			System.out.println("Starting #" + id +  " MP loop at: "+ this.timeStarted);
		}
		else if (isGeneratedCurr && isReverseCurr){

			this.resetEncoders();
			left.configureEncoder(RobotMap.driveLeft1.getSensorCollection().getQuadraturePosition(), 4096, 0.5); // 4096 magenc
//			left.configurePIDVA(0, 0, 0, 1 / 10, 0);

			left.configurePIDVA(MPConstants.kP, MPConstants.kI, MPConstants.kD, 1 / MPConstants.kV, MPConstants.kA);

			
			right.configureEncoder(RobotMap.driveRight1.getSensorCollection().getQuadraturePosition(), 4096, 0.5); //4096
			right.configurePIDVA(MPConstants.kP, MPConstants.kI, MPConstants.kD, 1 / MPConstants.kV, MPConstants.kA);
//			right.configurePIDVA(0, 0, 0, 1 / 10, 0);

			Robot.isDriveTrainMPActive = true;
			this.timeStarted = Timer.getFPGATimestamp();
			System.out.println("Starting #" + id +  " MP loop at: "+ this.timeStarted);
			System.out.println("!!!!!REVERSE!!!!!");
		}else{
			System.out.println("NOT GENERATED!!!");
		}

		if(RobotMap.activeSide == AutoSide.LEFT){
			RobotMap.isDoneMPL[currID] = false;
		}else{
			RobotMap.isDoneMPR[currID] = false;
		}
		
    }
    public boolean collisionDetected(){
        double curr_world_linear_accel_x = RobotMap.ahrs.getWorldLinearAccelX();
        double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        double curr_world_linear_accel_y = RobotMap.ahrs.getWorldLinearAccelY();
        double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
//        System.out.println("X Jerk " + Math.abs(currentJerkX));
//        System.out.println("Y Jerk " + Math.abs(currentJerkY));
        if ( ( Math.abs(currentJerkX) > 0.5 ) ||
             ( Math.abs(currentJerkY) > 0.5) ) {
            return true;
        }else{
        	return false;
        }
    }
}

