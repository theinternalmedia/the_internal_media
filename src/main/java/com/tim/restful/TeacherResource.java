package com.tim.restful;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.teacher.TeacherDto;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.Teacher.PREFIX)
public class TeacherResource extends AbstractResource {
	
	@PostMapping(TimApiPath.Teacher.SAVE)
	public String save(@Valid @RequestBody TeacherDto teacherDto) {
		return "success";
	}
	
}