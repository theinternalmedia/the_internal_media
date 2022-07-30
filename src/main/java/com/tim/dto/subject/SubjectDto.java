package com.tim.dto.subject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

/**
 * 
 * @appName the_internal_media
 *
 */
public class SubjectDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1295707391155869293L;

	@NotBlank
	@Size(max = 20)
	private String code;

	@NotBlank
	@Size(max = 50)
	private String name;

	@Min(value = 1)
	private int numberOfCredits = 1;

	private boolean mandatory = true;

	@Size(min = 0, max = 10)
	private float passMarks;

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

	public float getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(float passMarks) {
		this.passMarks = passMarks;
	}
}
