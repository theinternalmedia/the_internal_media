package com.tim.service;

import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectUpdateDto;

public interface EduProgramSubjectService {
	EducationProgramSubjectDto update(EducationProgramSubjectUpdateDto updateDto);
	
	EducationProgramSubjectDto getOneBySubjectCodeAndEduProgramCode(String subjectCode, String eduProgramCode);
}
