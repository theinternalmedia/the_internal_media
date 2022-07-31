package com.tim.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.tim.converter.TeacherConverter;
import com.tim.dto.teacher.TeacherDto;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;

@Service
@Validated
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;

	@Override
	public long save(@Valid TeacherDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TeacherDto findByUserId(@NotBlank String userId) {
		// TODO Auto-generated method stub
		return teacherConverter.toDto(teacherRepository.findByUserId(userId)
				.orElseThrow(()-> new EntityNotFoundException("Không tìm thấy người dùng với id = " + userId)));
	}

}
