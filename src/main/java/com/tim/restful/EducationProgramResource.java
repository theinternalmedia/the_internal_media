package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.Permissions;
import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.educationprogram.EducationProgramCreateDto;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramPageRequestDto;
import com.tim.dto.educationprogram.EducationProgramResponseDto;
import com.tim.dto.educationprogram.EduProgramAndSubjectResponseDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;
import com.tim.service.EduProgramService;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class EducationProgramResource {
	
	@Autowired
	private EduProgramService eduProgramService;
	
	@PreAuthorize("hasAuthority('" + Permissions.EducationProgram.CREATE + "')")
	@PostMapping(value = TimApiPath.EducationProgram.CREATE)
	public EducationProgramDto create(
			@RequestPart(value = "file", required = false) MultipartFile file,
			EducationProgramCreateDto requestDto) {

		return eduProgramService.create(requestDto, file);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.EducationProgram.UPDATE + "')")
	@PutMapping(value = TimApiPath.EducationProgram.UPDATE)
	public EducationProgramResponseDto update(
			@RequestPart(value = "file", required = false) MultipartFile file,
			EducationProgramUpdateDto updateDto) {
		
		return eduProgramService.update(updateDto, file);
	}
	
	@GetMapping(value = TimApiPath.EducationProgram.GET_BY_CODE)
	public EducationProgramDto getById(@RequestParam String code) {
		return eduProgramService.getOne(code);
	}
	
	@GetMapping(value = TimApiPath.EducationProgram.GET_SUBJECT_LIST)
	public EduProgramAndSubjectResponseDto getSubjectList(@RequestParam String code) {
		return eduProgramService.getSubjectList(code);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.EducationProgram.TOGGLE_STATUS + "')")
	@PutMapping(value = TimApiPath.EducationProgram.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return eduProgramService.toggleStatus(ids);
	}
	
	@GetMapping(value = TimApiPath.EducationProgram.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPaging(
			EducationProgramPageRequestDto pageRequestDto){
		return ResponseEntity.ok(eduProgramService.getPage(pageRequestDto));
	}
}
