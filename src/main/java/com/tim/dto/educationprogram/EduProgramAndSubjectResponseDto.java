package com.tim.dto.educationprogram;

import java.util.ArrayList;
import java.util.List;

import com.tim.dto.educationprogramsubject.EducationProgramSubjectResponseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class EduProgramAndSubjectResponseDto extends EducationProgramDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -6023941757152340157L;
	
	private List<EducationProgramSubjectResponseDto> educationProgramSubjectDtos = new ArrayList<>(); 
	
	private int totalCredits;

}
