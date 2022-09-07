package com.tim.service;

import java.util.Set;

import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyRequestDto;

public interface FacultyService {
	FacultyDto create(FacultyRequestDto facultyDto);
	
	long toggleStatus(Set<Long> ids);
}
