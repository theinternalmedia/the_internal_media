package com.tim.service;

import java.util.Set;

import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearRequestDto;
import com.tim.dto.schoolyear.SchoolYearUpdateRequestDto;

public interface SchoolYearService {
	SchoolYearDto create(SchoolYearRequestDto requestDto);

	SchoolYearDto update(SchoolYearUpdateRequestDto requestDto);
	
	SchoolYearDto getOne(String code);

	long toggleStatus(Set<Long> ids);
	
}
