package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramPageRequestDto;
import com.tim.dto.educationprogram.EducationProgramRequestDto;
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
	EducationProgramDto create(EducationProgramRequestDto eduProgramRequestDto, MultipartFile file);
	
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
	
	PagingResponseDto getPage(/*
								 * int page, int size, boolean status, String code, String name, String
								 * facultyCode, String schoolYearCode
								 */
			EducationProgramPageRequestDto pageRequestDto);
	
	Long toggleStatus(Long id);
	
	EducationProgramResponseDto getSubjectList(String code);

}
