package com.tim.service;

import org.springframework.stereotype.Service;

import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassRequestDto;

@Service
public interface ClassService {
	ClassDto create(ClassRequestDto requestDto);
}
