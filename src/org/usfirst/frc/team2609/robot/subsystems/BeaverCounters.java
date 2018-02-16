package org.usfirst.frc.team2609.robot.subsystems;

import enums.CounterType;

public class BeaverCounters {
	CounterType type;
	double count;
	public BeaverCounters(CounterType type){
		this.type = type;
	}
	public void changeMode(CounterType type){
		this.type = type;
	}
	public CounterType getMode(CounterType type){
		return this.type;
	}
	public void setLimit(double count){
		this.count = count;
	}
	public boolean count(boolean argument){
		switch(type){
			case LOOPCOUNT:
				return true;
			case TIME:
				return false;
			default:
				return true;
				
		}
	}
}
