package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.faculty.FacultyDto;

public interface FacultyService {
	ResponseDto create(FacultyDto dto);
}
