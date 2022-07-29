package com.tim.dto;

import java.io.Serializable;

public class ScoresDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2099206156759347473L;
	
	private long id;
	private float finalScores;
	private int times;
	private boolean pass;
	private String note;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getFinalScores() {
		return finalScores;
	}
	public void setFinalScores(float finalScores) {
		this.finalScores = finalScores;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "ScoreDto [id=" + id + ", finalScores=" + finalScores + ", times=" + times + ", pass=" + pass + ", note="
				+ note + "]";
	}
}
