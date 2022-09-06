package com.tim.dto.educationprogramsubject;

import javax.validation.constraints.NotBlank;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationProgramSubjectDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -3392594904464830062L;

	private String note;
	
	@NotBlank
	private String semester;
	
	@NotBlank
	private String subjectCode;
	
	@NotBlank
	private String subjectName;
}
