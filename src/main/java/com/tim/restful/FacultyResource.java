package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.faculty.FacultyDto;
import com.tim.service.FacultyService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class FacultyResource {
	@Autowired
	private FacultyService facultyService;
	
	@PostMapping(TimApiPath.Faculty.CREATE)
	public FacultyDto create(@RequestBody FacultyDto facultyDto) {
		return facultyService.create(facultyDto);
	}
}
