package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.Permissions;
import com.tim.data.TimApiPath;
import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassRequestDto;
import com.tim.service.ClassService;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class ClassResource {
	@Autowired
	private ClassService classService;
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.CREATE + "')")
	@PostMapping(TimApiPath.Class.CREATE)
	public ClassDto create(@RequestBody ClassRequestDto requestDto) {
		return classService.create(requestDto);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.TOGGLE_STATUS + "')")
	@PutMapping(TimApiPath.Class.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return classService.toggleStatus(ids);
	}
}
