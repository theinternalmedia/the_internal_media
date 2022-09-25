package com.tim.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramPageRequestDto;
import com.tim.dto.educationprogram.EducationProgramCreateDto;
import com.tim.dto.educationprogram.EducationProgramResponseDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;

public interface EduProgramService {
	/**
	 * 
	 * @author thinh
	 * @param eduProgramRequestDto
	 * @param file
	 * @return EducationProgramDto
	 */
	EducationProgramDto create(EducationProgramCreateDto eduProgramRequestDto, MultipartFile file);
	
	/**
	 * 
	 * @author thinh
	 * @param education
	 * @param file
	 * @return EducationProgramDto
	 */
	EducationProgramDto update(EducationProgramUpdateDto education, MultipartFile file);
	
	/**
	 * 
	 * @author thinh
	 * @param code
	 * @return EducationProgramDto
	 */
	EducationProgramDto getOne(String code);
	
	PagingResponseDto getPage(EducationProgramPageRequestDto pageRequestDto);
	
	long toggleStatus(Set<Long> ids);
	
	EducationProgramResponseDto getSubjectList(String code);

}
