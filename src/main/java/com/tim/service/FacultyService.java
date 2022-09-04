package com.tim.service;

import java.util.Set;

import com.tim.dto.faculty.FacultyDto;

public interface FacultyService {
	FacultyDto create(FacultyDto dto);
	
	long toggleStatus(Set<Long> ids);
}
