package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherRequestDto;
import com.tim.dto.teacher.TeacherUpdateRequestDto;

public interface TeacherService {
	/**
	 * @author minhtuanitk43
	 * @param dto
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
	TeacherDto create(TeacherRequestDto requestDto);

	/**
	 * @author minhtuanitk43
	 * @param userId
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
	TeacherDto findByUserId(String userId);

	/**
	 * @author minhtuanitk43
	 * @param file excel file
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
	long create(MultipartFile file);
	
	String exportToExcelFile();

	/**
	 * @author minhtuanitk43
	 * @param avatar
	 * @return image path
	 */
	String updateAvatar(MultipartFile avatar);
	
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
	
	TeacherDto getOne(String userId);

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @return ResponseDto
	 */
	TeacherDto update(TeacherUpdateRequestDto requestDto);

	Long toggleStatus(Long id);

}
