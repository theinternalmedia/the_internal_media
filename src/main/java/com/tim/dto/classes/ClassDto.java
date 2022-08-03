package com.tim.dto.classes;

import javax.validation.constraints.Size;

import com.tim.data.ValidationInput;
import com.tim.dto.BaseDto;

public class ClassDto extends BaseDto {

	/**
	 * thinhnguyen
	 */
	private static final long serialVersionUID = -8648320837995659089L;

	@Size(max = 50, message = ValidationInput.General.CODE_MAX_SIZE)
	private String code;

	@Size(max = 50, message = ValidationInput.General.NAME_MAX_SIZE)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}