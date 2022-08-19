package com.tim.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.entity.Student;

public interface StudentService {
    ResponseDto create(StudentRequestDto studentRequestDto);
    
    /**
	 * @author minhtuanitk43
	 * @param file excel file
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
    ResponseDto create(MultipartFile file);
    
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

    ResponseDto update(StudentDto dto);

    ResponseDto getOne(Long id);

    ResponseDto getByUserName(String name);

    ResponseDto getByEmail(String email);

    ResponseDto toggleStatus(Long id);
}
