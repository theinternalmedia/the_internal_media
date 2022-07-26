package com.tim.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.PasswordDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentPageRequestDto;
import com.tim.dto.student.StudentCreateDto;
import com.tim.dto.student.StudentUpdateProfileDto;
import com.tim.dto.student.StudentUpdateDto;
import com.tim.entity.Student;

public interface StudentService {
    StudentDto create(StudentCreateDto requestDto);
    
    /**
	 * @author minhtuanitk43
	 * @param file excel file
	 */
    long create(MultipartFile file);
    
    StudentDto update(StudentUpdateDto requestDto);
    
    StudentDto updateProfile(StudentUpdateProfileDto updateDto);
    
    /**
	 * @author minhtuanitk43
	 * @param schoolYearCodes
	 * @param facultyCodes
	 * @param classCodes
	 * @return list Student
	 */
	List<Student> findByChoolYearAndFacultyAndClass(Set<String> schoolYearCodes, Set<String> facultyCodes,
			Set<String> classCodes);
	
	String exportToExcelFile();

	String updateAvatar(MultipartFile avatar);

	StudentDto getOne(Long id);

	long toggleStatus(Set<Long> ids);
	
	/**
	 * 
	 * @author thinh
	 * @param userId
	 * @return StudentDto
	 */
	StudentDto getByUserId(String userId);
	
	PagingResponseDto getPage(StudentPageRequestDto pageRequestDto);
	
	void updatePassword(PasswordDto passwordDto);

}
