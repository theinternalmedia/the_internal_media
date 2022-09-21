package com.tim.dto.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StudentPageRequestDto {

	@NotBlank
	@Min(value = 1)
	private int page;

	@NotBlank
	@Min(value = 1)
	private int size;

	private String userId;

	private String name;
	
	private String classCode;

	@NotBlank
	private Boolean status;
}
