package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentPageRequestDto;
import com.tim.dto.student.StudentCreateDto;
import com.tim.dto.student.StudentUpdateDto;
import com.tim.service.StudentService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping(TimApiPath.Student.UPLOAD_EXCEL)
	public long uploadExcelFile(
				@RequestPart(value = "file", required = false) MultipartFile file) {
		return studentService.create(file);
	}
	
	@PostMapping(TimApiPath.Student.CREATE)
	public StudentDto create(
			@ApiParam("StudentRequestDto to create new Student")
			@RequestBody StudentCreateDto requestDto) {
		return studentService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.Student.UPDATE)
	public StudentDto update(
			@ApiParam("StudentRequestDto to update Student")
				@RequestBody StudentUpdateDto requestDto) {
		return studentService.update(requestDto);
	}
	
	@GetMapping(TimApiPath.Student.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPage(
			StudentPageRequestDto pageRequestDto) {
		return ResponseEntity.ok(studentService.getPage(pageRequestDto));
	}
	
	@PutMapping(TimApiPath.Student.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return studentService.toggleStatus(ids);
	}
}
