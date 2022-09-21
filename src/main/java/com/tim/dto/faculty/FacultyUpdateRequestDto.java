package com.tim.dto.faculty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.Data;


@Data
public class FacultyUpdateRequestDto {

	@NotNull
	@Min(value = 1)
	private Long id;

	@Size(max = 20, min = 5)
	@NotBlank
	@Code
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;

	private String headOfFacultyUserId;
}
