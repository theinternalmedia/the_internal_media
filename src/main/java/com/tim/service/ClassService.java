package com.tim.service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tim.dto.ResponseDto;
import com.tim.dto.classes.ClassDto;
import com.tim.dto.teacher.TeacherDto;

public interface ClassService {
	ResponseDto save(@Valid ClassDto classDto);
	ResponseDto findByUserId(@NotBlank String userId);
	ResponseDto update(@RequestBody ClassDto classDto, @PathVariable("id") long id);

}
