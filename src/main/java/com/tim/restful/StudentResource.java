package com.tim.restful;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.data.TimConstants;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.dto.student.StudentUpdateRequestDto;
import com.tim.service.StudentService;

import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping(TimApiPath.Student.UPLOAD_EXCEL)
	public String uploadExcelFile(
				@RequestPart("file") MultipartFile file) {
		studentService.create(file);
		return TimConstants.SUCCESS;
	}
	
	@PostMapping(TimApiPath.Student.CREATE)
	public StudentDto create(
			@ApiParam("StudentRequestDto to create new Student")
				@RequestBody StudentRequestDto requestDto) {
		return studentService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.Student.UPDATE)
	public StudentDto update(
			@ApiParam("StudentRequestDto to update Student")
				@RequestBody StudentUpdateRequestDto requestDto) {
		return studentService.update(requestDto);
	}
	
	@PutMapping(TimApiPath.Student.TOGGLE_STATUS)
	public Long toggleStatus(@PathParam("id") Long id) {
		return studentService.toggleStatus(id);
	}
	
}
