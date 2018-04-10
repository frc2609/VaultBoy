package org.usfirst.frc.team2609;

import edu.wpi.first.wpilibj.DigitalInput;

public class BeaverDigitalInput extends DigitalInput {
	private boolean _isInverted = false;
	
	public BeaverDigitalInput(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean get(){
		return (_isInverted)? !super.get() : super.get();
	}
	
	public void setInverted(boolean isInverted){
		this._isInverted = isInverted;
	}
}
