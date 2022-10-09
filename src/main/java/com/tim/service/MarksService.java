package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.marks.MarksCreateDto;
import com.tim.dto.marks.MarksDto;

public interface MarksService {

	MarksDto create(MarksCreateDto requestDto);

	long create(MultipartFile file);
	
	String exportToExcelByStudentId(String userId);
	
	String exportToExcelBySubjectAndClass(String subject, String classCode);
}
