package com.tim.service;

import java.util.List;
import java.util.Set;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyUpdateRequestDto;

public interface FacultyService {
	FacultyDto create(FacultyDto dto);
	
	long toggleStatus(Set<Long> ids);
	/**
	 * 
	 * @author thinh
	 * @param code
	 * @return FacultyDto
	 */
	FacultyDto getByCode(String code);
	
	/**
	 * 
	 * @author thinh
	 * @param updateDto
	 * @return FacultyDto
	 */
	FacultyDto update(FacultyUpdateRequestDto updateDto);
	
	/**
	 * 
	 * @author thinh
	 * @param status
	 * @return List<FacultyDto>
	 */
	List<FacultyDto> getAll(boolean status);
	
	/**
	 * 
	 * @author thinh
	 * @param status
	 * @param code
	 * @param name
	 * @param page
	 * @param size
	 * @return PagingResponseDto
	 */
	PagingResponseDto getPage(int page, int size, String status, String code, String name);
	
}
