package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.service.StudentService;

import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class StudentResource {
	@Autowired
	private StudentService studentService;
	
	@PostMapping(TimApiPath.Student.UPLOAD_EXCEL)
	public ResponseDto uplaodExcelFile(
				@RequestPart("file") MultipartFile file) {
		return studentService.create(file);
	}

	@PostMapping(TimApiPath.Student.UPLOAD_STUDENT)
	public ResponseDto updloadStudent(@RequestParam("studentDtoJsonRequest") String studentDtoJsonRequest,
									  @RequestPart(value = "image", required = false) MultipartFile image){
		return studentService.upload(studentDtoJsonRequest, image);
	}
	
	@PostMapping(TimApiPath.Student.CREATE)
	public ResponseDto create(
			@ApiParam("StudentRequestDto to create new Student")
				@RequestBody StudentRequestDto requestDto) {
		return studentService.create(requestDto);
	}
}
