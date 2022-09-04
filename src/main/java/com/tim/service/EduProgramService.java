package com.tim.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramRequestDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;

@Service
public interface EduProgramService {
	
	
	/**
	 * 
	 * @author thinh
	 * @param eduProgramRequestDto
	 * @param file
	 * @return EducationProgramDto
	 */
	EducationProgramDto create(EducationProgramRequestDto eduProgramRequestDto, MultipartFile file);
	
	/**
	 * 
	 * @author thinh
	 * @param education
	 * @param file
	 * @return EducationProgramDto
	 */
	EducationProgramDto update(EducationProgramUpdateDto education, MultipartFile file);
	
	EducationProgramDto getByCode(String code);
	
	Long toggleStatus(Long id);
	
	EducationProgramSubjectDto getBySemesterAndEduProgramCode(String semester, String code);
}
