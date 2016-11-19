package com.lenovo.push.data.serving.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AvazuEntity extends BaseJsonEntity {
	private String thedate;
	private String errCode;
	private int value;
	
	public AvazuEntity() {
		
	}
	
	public AvazuEntity(int value) {
		this.value = value;
	}
	
	public AvazuEntity(String errCode, int value) {
		this.errCode = errCode;
		this.value = value;
	}

	public String getThedate() {
		return thedate;
	}

	public void setThedate(String thedate) {
		this.thedate = thedate;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
