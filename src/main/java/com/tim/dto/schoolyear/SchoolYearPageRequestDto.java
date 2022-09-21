package com.tim.dto.schoolyear;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SchoolYearPageRequestDto {

	@NotBlank
	@Min(value = 1)
	private int page;

	@NotBlank
	@Min(value = 1)
	private int size;

	private String code;

	private String name;

	@NotBlank
	private Boolean status;
}
