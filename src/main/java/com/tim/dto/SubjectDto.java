package com.tim.dto;

import java.io.Serializable;

public class SubjectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1295707391155869293L;
	
	private long id;	
	private String code;	
	private String name;	
	private int numberOfCredits;
	private boolean mandatory;
	private float passScores;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "SubjectDto [id=" + id + ", code=" + code + ", name=" + name + ", numberOfCredits=" + numberOfCredits
				+ ", mandatory=" + mandatory + ", passScores=" + passScores + "]";
	}

}
