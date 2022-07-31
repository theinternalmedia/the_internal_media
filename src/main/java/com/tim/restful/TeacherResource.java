package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.teacher.TeacherDto;
import com.tim.service.TeacherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.Teacher.PREFIX)
public class TeacherResource extends AbstractResource {
	
	@Autowired
	private TeacherService teacherService;
	@PostMapping(TimApiPath.Teacher.SAVE)
	public String save(@RequestBody TeacherDto teacherDto) {
		return String.valueOf(teacherService.save(teacherDto));
	}
	
	@GetMapping(TimApiPath.Teacher.GET)
	public TeacherDto get(@RequestParam("userId") String userId) {
		return teacherService.findByUserId(userId);
	}
	
}