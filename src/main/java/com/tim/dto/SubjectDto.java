package com.tim.dto;

import java.io.Serializable;

public class SubjectDto extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1295707391155869293L;

	private String code;	
	private String name;	
	private int numberOfCredits;
	private boolean mandatory;
	private float passScores;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfCredits() {
		return numberOfCredits;
	}
	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public float getPassScores() {
		return passScores;
	}
	public void setPassScores(float passScores) {
		this.passScores = passScores;
	}


}
