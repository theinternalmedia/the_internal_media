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
import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearRequestDto;
import com.tim.dto.schoolyear.SchoolYearUpdateRequestDto;
import com.tim.service.SchoolYearService;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class SchoolYearResource {
	@Autowired
	private SchoolYearService schoolYearService;
	
	@PostMapping(TimApiPath.SchoolYear.CREATE)
	public SchoolYearDto create(@RequestBody SchoolYearRequestDto requestDto) {
		return schoolYearService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.SchoolYear.UPDATE)
	public SchoolYearDto update(@RequestBody SchoolYearUpdateRequestDto requestDto) {
		return schoolYearService.update(requestDto);
	}
	
	@PutMapping(TimApiPath.SchoolYear.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return schoolYearService.toggleStatus(ids);
	}
}
