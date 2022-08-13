package com.tim.service;

import com.tim.dto.classes.ClassDto;
import org.springframework.stereotype.Service;

@Service
public interface ClassService {
	long save(ClassDto classDto);
}
