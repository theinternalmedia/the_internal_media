package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping(TimApiPath.Student.PREFIX)
public class StudentResource {
	@Autowired
	private StudentService studentService;
	
	@PostMapping(TimApiPath.Student.UPLOAD_EXCEL)
	public ResponseDto uplaodExcelFile(@RequestPart("file") MultipartFile file) {
		return studentService.create(file);
	}
	
	@PostMapping(TimApiPath.Student.CREATE)
	public ResponseDto create(@RequestBody StudentRequestDto requestDto) {
		return studentService.create(requestDto);
	}
}
