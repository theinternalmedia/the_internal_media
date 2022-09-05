package com.tim.dto.educationprogramsubject;

import javax.validation.constraints.NotNull;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationProgramSubjectDto extends BaseDto{

	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -6023941757152340157L;
	

	private String note;
	
	@NotNull
	private String semester;
	
	@NotNull
	private String subjectCode;
	
	@NotNull
	private String subjectName;
}
