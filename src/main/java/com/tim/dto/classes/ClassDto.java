package com.tim.dto.classes;

import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

public class ClassDto extends BaseDto {

	/**
	 * thinhnguyen
	 */
	private static final long serialVersionUID = -8648320837995659089L;

	@Size(max = 50)
	private String name;

	@Size(max = 20)
	private String code;

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