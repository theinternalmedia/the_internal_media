package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectUpdateDto;
import com.tim.service.EduProgramSubjectService;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class EduProgramSubjectResource {
	
	@Autowired
	EduProgramSubjectService eduProgramSubjectService;
	
	
	@GetMapping(TimApiPath.EducationProgramSubject.GET)
	public EducationProgramSubjectDto getBySubjectCodeAndEduProgramCode(
									@RequestParam("subjectCode") String subjectCode,
									@RequestParam("eduProgramCode") String eduProgramCode) {
		return eduProgramSubjectService.getOneBySubjectCodeAndEduProgramCode(subjectCode, eduProgramCode);
	}
	
	@PutMapping(TimApiPath.EducationProgramSubject.UPDATE)
	public EducationProgramSubjectDto update(@RequestBody EducationProgramSubjectUpdateDto updateDto) {
		return eduProgramSubjectService.update(updateDto);
	}
	
}
