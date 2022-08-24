package com.tim.restful;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.dto.student.StudentUpdateRequestDto;
import com.tim.service.StudentService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(TimApiPath.Student.UPLOAD_IMAGE)
	public ResponseDto uploadAvatar(@RequestPart("avatar") MultipartFile avatar,
									@RequestParam("userId") String userId){
		return studentService.updateAvatar(avatar, userId);
	}

	
	@PostMapping(TimApiPath.Student.CREATE)
	public ResponseDto create(
			@ApiParam("StudentRequestDto to create new Student")
				@RequestBody StudentRequestDto requestDto) {
		return studentService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.Student.UPDATE)
	public ResponseDto update(
			@ApiParam("StudentRequestDto to update Student")
				@RequestBody StudentUpdateRequestDto requestDto) {
		return studentService.update(requestDto);
	}
}
