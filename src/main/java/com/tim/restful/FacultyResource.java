package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.faculty.FacultyDto;
import com.tim.service.FacultyService;


@RestController
@RequestMapping(TimApiPath.TIM_API)
public class FacultyResource {
	@Autowired
	private FacultyService facultyService;
	
	@PostMapping(TimApiPath.Faculty.CREATE)
	public FacultyDto create(@RequestBody FacultyDto facultyDto) {
		return facultyService.create(facultyDto);
	}
	
	@PutMapping(TimApiPath.Faculty.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return facultyService.toggleStatus(ids);
	}
}
