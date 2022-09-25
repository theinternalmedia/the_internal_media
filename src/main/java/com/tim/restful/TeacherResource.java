package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherPageRequestDto;
import com.tim.dto.teacher.TeacherRequestDto;
import com.tim.dto.teacher.TeacherUpdateRequestDto;
import com.tim.service.TeacherService;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class TeacherResource {
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(TimApiPath.Teacher.CREATE)
	public TeacherDto create(@RequestBody TeacherRequestDto requestDto) {
		return teacherService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.Teacher.UPDATE)
	public TeacherDto update(@RequestBody TeacherUpdateRequestDto requestDto) {
		return teacherService.update(requestDto);
	}
	
	@PostMapping(TimApiPath.Teacher.UPLOAD_EXCEL)
	public long uplaodExcelFile(@RequestPart("file") MultipartFile file) {
		return teacherService.create(file);
	}
	
	@GetMapping(TimApiPath.Teacher.GET_BY_USERID)
	public TeacherDto getByUserId(@RequestParam("userId") String userId){
		return teacherService.getOne(userId);
	}

	@GetMapping(TimApiPath.Teacher.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPage(TeacherPageRequestDto pageRequestDto){
		return ResponseEntity.ok(teacherService.getPage(pageRequestDto));
	}
	
	@PutMapping(TimApiPath.Teacher.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return teacherService.toggleStatus(ids);
	}
}