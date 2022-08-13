package com.tim.restful;

import com.tim.dto.teacher.TeacherDto;
import com.tim.utils.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import com.tim.data.ETimMessages;
import com.tim.utils.Utility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestResource extends AbstractResource {

	@GetMapping("/message")
	public String testGetExceptionMessage() {
		return Utility.getMessage(ETimMessages.INTERNAL_SYSTEM_ERROR);
	}

	@PostMapping("/message/teacher")
	public String testExceptionMessage(@RequestBody TeacherDto teacherDto) {
		ValidationUtils.validateObject(teacherDto);
		return Utility.getMessage(ETimMessages.ACCESS_DENIED);
	}
}