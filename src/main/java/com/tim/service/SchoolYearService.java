package com.tim.service;

import java.util.List;
import java.util.Set;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearPageRequestDto;
import com.tim.dto.schoolyear.SchoolYearCreateDto;
import com.tim.dto.schoolyear.SchoolYearUpdateDto;

public interface SchoolYearService {
	SchoolYearDto create(SchoolYearCreateDto requestDto);

	SchoolYearDto update(SchoolYearUpdateDto requestDto);
	
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
	PagingResponseDto getPage(SchoolYearPageRequestDto pageRequestDto);

	long toggleStatus(Set<Long> ids);
	
}
