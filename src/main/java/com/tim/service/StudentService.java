package com.tim.service;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentDto;

public interface StudentService {
    ResponseDto save(StudentDto studentDto);
    
    /**
	 * @author minhtuanitk43
	 * @param file excel file
	 * @return ResponseDto include status ok if success, else not ok. message and data if have return data
	 */
    ResponseDto save(MultipartFile file);
}
