package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.schoolyear.SchoolYearDto;

public interface SchoolYearService {
	ResponseDto insert(SchoolYearDto dto);
}
