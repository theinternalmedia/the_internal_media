package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.data.TimApiPath;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramRequestDto;
import com.tim.dto.educationprogram.EducationProgramResponseDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;
import com.tim.service.EduProgramService;
import com.tim.utils.Utility;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class EducationProgramResource {
	
	@Autowired
	EduProgramService eduProgramService;
	
	@PostMapping(value = TimApiPath.EducationProgram.CREATE)
	public EducationProgramDto create(
			@RequestParam("eduProgramRequestJson") String eduProgramRequestJson,
			@RequestPart("file") MultipartFile file) {
		EducationProgramRequestDto requestDto = 
				Utility.convertStringJsonToObject(
						eduProgramRequestJson, 
						new TypeReference<EducationProgramRequestDto>() {});
		return eduProgramService.create(requestDto, file);
	}
	
	@PutMapping(value = TimApiPath.EducationProgram.UPDATE)
	public EducationProgramDto update(@RequestParam("eduProgramUpdateJson") String eduProgramUpdateJson,
									@RequestPart(value = "file", required = false) MultipartFile file	) {
		EducationProgramUpdateDto updateDto = Utility.convertStringJsonToObject(eduProgramUpdateJson, 
				new TypeReference<EducationProgramUpdateDto>() {});
		return eduProgramService.update(updateDto, file);
	}
	
	@GetMapping(value = TimApiPath.EducationProgram.GET_BY_CODE)
	public EducationProgramDto getById(@RequestParam String code) {
		return eduProgramService.getOne(code);
	}
	
	@GetMapping(value = TimApiPath.EducationProgram.GET_SUBJECT_LIST)
	public EducationProgramResponseDto getSubjectList(@RequestParam String code) {
		return eduProgramService.getSubjectList(code);
	}
	
	@PutMapping(value = TimApiPath.EducationProgram.TOGGLE_STATUS)
	public Long toggleStatus(@PathVariable("id") Long id) {
		return eduProgramService.toggleStatus(id);
	}
}
