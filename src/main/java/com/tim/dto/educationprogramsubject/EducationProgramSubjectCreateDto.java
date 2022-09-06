package com.tim.dto.educationprogramsubject;

import javax.validation.constraints.NotBlank;

import com.tim.annotation.Code;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class EducationProgramSubjectCreateDto {

	private String note;
	
	@NotBlank
	private String semester;
	
	@NotBlank
	@Code
	private String subjectCode;
	
}
