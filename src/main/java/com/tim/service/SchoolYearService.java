package com.tim.service;

import java.util.List;
import java.util.Set;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearRequestDto;
import com.tim.dto.schoolyear.SchoolYearUpdateRequestDto;

public interface SchoolYearService {
	SchoolYearDto create(SchoolYearRequestDto requestDto);

	SchoolYearDto update(SchoolYearUpdateRequestDto requestDto);
	
	SchoolYearDto getOne(String code);
	
	/**
	 * 
	 * @author thinh
	 * @return List<SchoolYearDto>
	 */
	List<SchoolYearDto> getAll(boolean status);
	
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
	PagingResponseDto getPage(int page, int size, boolean status, String code, String name);

	long toggleStatus(Set<Long> ids);
	
}
