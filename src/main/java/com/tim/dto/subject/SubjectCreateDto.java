package com.tim.dto.subject;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.Data;

@Data
public class SubjectCreateDto {

	@Size(max = 20)
	@NotBlank
	@Code
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;

	@NotNull
	@Min(1)
	@Max(10)
	private int numberOfCredits;

	@NotNull
	private Boolean mandatory;

	@Max(10)
	@Min(0)
	@NotNull
	private Float passMarks;
}
