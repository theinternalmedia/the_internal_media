package com.tim.service;

import org.springframework.stereotype.Service;

import com.tim.dto.ResponseDto;
import com.tim.dto.classz.ClassDto;

@Service
public interface ClassService {
	ResponseDto insert(ClassDto classDto);
}
