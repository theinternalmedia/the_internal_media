package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.marks.MarksDto;
import com.tim.dto.marks.MarksRequestDto;

public interface MarksService {

	MarksDto create(MarksRequestDto requestDto);

	long create(MultipartFile file);
	
}
