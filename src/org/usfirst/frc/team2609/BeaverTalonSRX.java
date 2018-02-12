package org.usfirst.frc.team2609;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BeaverTalonSRX extends WPI_TalonSRX {
	private double encPerRevs;
	public BeaverTalonSRX(int deviceNumber) {
		super(deviceNumber);
		// TODO Auto-generated constructor stub
	}
	public void setEncPerRevs(double encPerRevs){
		this.encPerRevs = encPerRevs;
	}
	public void setEncPerRevsQuad(double encPerRevsQuad){
		this.encPerRevs = 4*encPerRevsQuad;
	}
	public double getPosition(){
		return super.getSensorCollection().getQuadraturePosition()/encPerRevs;
	}
	public double get(){
		return super.getSensorCollection().getQuadraturePosition();
	}
	public double getVelocity(){
		return super.getSensorCollection().getQuadratureVelocity()/encPerRevs;
	}
//	public void set(double percent){
//		super.set(ControlMode.PercentOutput, percent); // Dangerous as some parts of code might still be assuming TalonControlMode has been set
	// need setTalonControlMode
//	}
	
	public int getEncPosition(){
		return super.getSensorCollection().getQuadraturePosition();
	}
	public double getOutputVoltage(){
		return super.getMotorOutputVoltage();
	}
	
}
