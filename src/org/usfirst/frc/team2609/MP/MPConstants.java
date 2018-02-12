package org.usfirst.frc.team2609.MP;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MPConstants {
	public static double cruiseVelocity = 10;
	public static int rightEncPerFeet = 652;
	public static int leftEncPerFeet = 652;
	
	public static double kP,kI,kD,kV,kA;
	
	public static void sdPut(){
		SmartDashboard.putNumber("MP kP: ", 0.15);
		SmartDashboard.putNumber("MP kI: ", 0.001);
		SmartDashboard.putNumber("MP kD: ", 0.001);
		SmartDashboard.putNumber("MP kV: ", 10);
		SmartDashboard.putNumber("MP kA: ", 0.025);
	}
	public static void sdGet(){
		kP = SmartDashboard.getNumber("MP kP: ", 0.15); 
		kI = SmartDashboard.getNumber("MP kI: ", 0.001);
		kD = SmartDashboard.getNumber("MP kD: ", 0.001);
		kV = SmartDashboard.getNumber("MP kV: ", 10);   
		kA = SmartDashboard.getNumber("MP kA: ", 0.025);
	}
}
