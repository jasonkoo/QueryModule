package com.lenovo.push.data.serving.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StatResultEntity extends BaseJsonEntity {
	
	private String thedate;
	private int value;
	
	public StatResultEntity() {
		
	}
	
	public StatResultEntity(String thedate, int value) {
		setThedate(thedate);
		setValue(value);
	}
	
	public StatResultEntity(int value) {
		setValue(value);
	}
	
	public String getThedate() {
		return thedate;
	}
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}	
}
