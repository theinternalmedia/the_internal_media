package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.teacher.TeacherRequestDto;

public interface TeacherService {
	/**
	 * @author minhtuanitk43
	 * @param dto
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
	ResponseDto create(TeacherRequestDto requestDto);

	/**
	 * @author minhtuanitk43
	 * @param userId
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
	ResponseDto findByUserId(String userId);

	/**
	 * @author minhtuanitk43
	 * @param file excel file
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
	ResponseDto create(MultipartFile file);
	
	String exportToExcelFile();
	
	/**
	 * @author minhtuanitk43
	 * @param facultyCode
	 * @param name
	 * @param userId
	 * @param page
	 * @param size
	 * @return PagingResponseDto include totalItem, totalPage, page, size and content(data)
	 */
	PagingResponseDto getPage(String facultyCode, String name, String userId, int page, int size);
	
	ResponseDto getOne(String userId);

}
