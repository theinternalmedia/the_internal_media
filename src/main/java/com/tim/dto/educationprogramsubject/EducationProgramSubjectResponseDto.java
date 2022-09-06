package com.tim.dto.educationprogramsubject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class EducationProgramSubjectResponseDto {

	private String note;
	
	private String semester;
	
	private String subjectCode;
	
	private String subjectName;
	
	private int numberOfCredits;
	
	private boolean mandatory;
}
