package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;

public interface SchoolYearService {
	ResponseDto create(SchoolYearDto dto);

    ResponseDto update(SchoolYearDto dto);

    ResponseDto getOne(String code);

    void toggleStatus(Long id);
}
