package com.tim.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.PasswordDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherPageRequestDto;
import com.tim.dto.teacher.TeacherCreateDto;
import com.tim.dto.teacher.TeacherUpdateDto;
import com.tim.dto.teacher.TeacherUpdateProfileDto;

public interface TeacherService {
	/**
	 * @author minhtuanitk43
	 * @param dto
	 * @return TeacherDto include status ok if success, else not ok. message and data if have return data
	 */
	TeacherDto create(TeacherCreateDto requestDto);

	/**
	 * @author minhtuanitk43
	 * @param userId
	 * @return TeacherDto include status ok if success, else not ok. message and data if have return data
	 */
	TeacherDto getByUserId(String userId);

	/**
	 * @author minhtuanitk43
	 * @param file excel file
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
	PagingResponseDto getPage(TeacherPageRequestDto pageRequestDto);
	
	TeacherDto getOne(String userId);

	/**
	 * @author minhtuanitk43
	 * @param requestDto
	 * @return TeacherDto
	 */
	TeacherDto update(TeacherUpdateDto requestDto);
	
	TeacherDto updateProfile(TeacherUpdateProfileDto updateDto);

	long toggleStatus(Set<Long> ids);

	void updatePassword(PasswordDto passwordDto);

}
