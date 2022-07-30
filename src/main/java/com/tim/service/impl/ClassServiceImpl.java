package com.tim.service.impl;

import com.tim.converter.ClassConverter;
import com.tim.dto.classes.ClassDto;
import com.tim.entity.Class;
import com.tim.repository.ClassRepository;
import com.tim.service.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ClassConverter classConverter;
	@Override
	public long save(ClassDto classDto) {
		Class entity = classConverter.toEntity(classDto);
		return classRepository.save(entity).getId();
	}
}
