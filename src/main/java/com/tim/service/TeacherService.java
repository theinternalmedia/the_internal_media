package com.tim.service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.tim.dto.teacher.TeacherDto;

public interface TeacherService {
	long save(@Valid TeacherDto dto);
	TeacherDto findByUserId(@NotBlank String userId);
}
