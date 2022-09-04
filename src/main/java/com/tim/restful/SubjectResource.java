package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.dto.subject.SubjectDto;
import com.tim.dto.subject.SubjectRequestDto;
import com.tim.dto.subject.SubjectUpdateRequestDto;
import com.tim.service.SubjectService;

@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class SubjectResource {
	
	@Autowired
	private SubjectService subjectService;

	@PostMapping(TimApiPath.Subject.CREATE)
	public SubjectDto create(@RequestBody SubjectRequestDto requestDto) {
		return subjectService.create(requestDto);
	}
	
	@PostMapping(TimApiPath.Subject.UPLOAD_EXCEL)
	public long uploadExcelFile(@RequestPart("file") MultipartFile file) {
		return subjectService.create(file);
	}
	
	@PutMapping(TimApiPath.Subject.UPDATE)
	public SubjectDto update(@RequestBody SubjectUpdateRequestDto requestDto) {
		return subjectService.update(requestDto);
	} 
	
	@PutMapping(TimApiPath.Subject.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return subjectService.toggleStatus(ids);
	}
}
