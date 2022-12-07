package com.tim.dto.educationprogram;

import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.schoolyear.SchoolYearDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class EducationProgramResponseDto extends EducationProgramDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -6023941757152340157L;
	
	private FacultyDto faculty;
	
	private SchoolYearDto schoolYear;

}
