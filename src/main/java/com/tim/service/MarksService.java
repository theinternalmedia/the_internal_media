package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.marks.MarksCreateDto;
import com.tim.dto.marks.MarksDto;
import com.tim.dto.marks.MarksPageRequestDto;

public interface MarksService {

	MarksDto create(MarksCreateDto requestDto);

	long create(MultipartFile file);
	
	/**
	 * 
	 * @author thinh
	 * @param pageRequestDto
	 * @return PagingResponseDto
	 */
	PagingResponseDto getPaging(MarksPageRequestDto pageRequestDto);
	
	/**
	 * 
	 * @author thinh
	 * @param userId
	 * @return excel file path
	 */
	String exportToExcelByStudentId(String userId);
	
	/**
	 * 
	 * @author thinh
	 * @param subjectCode
	 * @param classCode
	 * @return excel file path
	 */
	String exportToExcelBySubjectAndClass(String subjectCode, String classCode);
}
