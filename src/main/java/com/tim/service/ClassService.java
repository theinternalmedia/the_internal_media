package com.tim.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassPageRequestDto;
import com.tim.dto.classz.ClassCreateDto;
import com.tim.dto.classz.ClassUpdateDto;

public interface ClassService {
	ClassDto create(ClassCreateDto requestDto);
	
	/**
	 * 
	 * @author thinh
	 * @param file
	 * @return long
	 */
	long create(MultipartFile file);
	
	/**
	 * 
	 * @author thinh
	 * @param code
	 * @return
	 */
	ClassDto getByCode(String code);
	
	/**
	 * 
	 * @author thinh
	 * @param pageRequestDto
	 * @return PagingResponseDto
	 */
	PagingResponseDto getPaging(ClassPageRequestDto pageRequestDto);
	
	/**
	 * 
	 * @author thinh
	 * @param dto
	 * @return ClassDto
	 */
	ClassDto update(ClassUpdateDto dto);
	
	long toggleStatus(Set<Long> ids);
}
