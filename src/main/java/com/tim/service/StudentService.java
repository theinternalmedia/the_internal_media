package com.tim.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.PasswordDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.dto.student.StudentUpdateRequestDto;
import com.tim.entity.Student;

public interface StudentService {
    StudentDto create(StudentRequestDto requestDto);
    
    /**
	 * @author minhtuanitk43
	 * @param file excel file
	 */
    void create(MultipartFile file);
    
    StudentDto update(StudentUpdateRequestDto requestDto);
    
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

	Long toggleStatus(Long id);
	
	/**
	 * 
	 * @author thinh
	 * @param userId
	 * @return StudentDto
	 */
	StudentDto getByUserId(String userId);
	
	void updatePassword(PasswordDto passwordDto);

}
