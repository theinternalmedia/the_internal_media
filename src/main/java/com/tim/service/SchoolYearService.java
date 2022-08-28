package com.tim.service;

import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearRequestDto;
import com.tim.dto.schoolyear.SchoolYearUpdateRequestDto;

public interface SchoolYearService {
	SchoolYearDto create(SchoolYearRequestDto requestDto);

	SchoolYearDto update(SchoolYearUpdateRequestDto requestDto);
	
	SchoolYearDto getOne(String code);

    Long toggleStatus(Long id);
	
}
