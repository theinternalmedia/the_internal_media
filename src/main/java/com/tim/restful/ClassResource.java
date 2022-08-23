package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.classz.ClassDto;
import com.tim.service.ClassService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class ClassResource {
	@Autowired
	private ClassService classService;
	
	@PostMapping(TimApiPath.Class.CREATE)
	public ResponseDto create(@RequestBody ClassDto classDto) {
		return classService.create(classDto);
	}
}