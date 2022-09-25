package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.marks.MarksDto;
import com.tim.dto.marks.MarksCreateDto;

public interface MarksService {

	MarksDto create(MarksCreateDto requestDto);

	long create(MultipartFile file);
	
}
