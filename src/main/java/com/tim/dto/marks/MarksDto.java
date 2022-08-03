package com.tim.dto.marks;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.tim.data.ValidationInput;
import com.tim.dto.BaseDto;

public class MarksDto extends BaseDto {

	private static final long serialVersionUID = -2099206156759347473L;

	@Size(min = 0, max = 10, message = ValidationInput.Mark.MARKS_MAX_SIZE)
	private float finalMarks;

	@Min(value = 1)
	private int times = 1;

	private boolean pass = false;

	private String note;

	public float getFinalMarks() {
		return finalMarks;
	}

	public void setFinalMarks(float finalMarks) {
		this.finalMarks = finalMarks;
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

}
