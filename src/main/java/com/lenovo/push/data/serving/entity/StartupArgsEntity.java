package com.lenovo.push.data.serving.entity;

public class StartupArgsEntity {
	private int hourDiff;
	
	public StartupArgsEntity(int hourDiff) {
		this.hourDiff = hourDiff;
	}

	public int getHourDiff() {
		return hourDiff;
	}

	public void setHourDiff(int hourDiff) {
		this.hourDiff = hourDiff;
	}
	
	
}
