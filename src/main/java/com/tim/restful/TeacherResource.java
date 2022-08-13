package com.tim.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.service.TeacherService;
import com.tim.utils.ValidationUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.Teacher.PREFIX)
public class TeacherResource extends AbstractResource {
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(TimApiPath.Teacher.SAVE)
	public ResponseDto save(@RequestBody TeacherDto teacherDto) {
		ValidationUtils.validateObject(teacherDto);
//		return ResponseEntity.ok(teacherService.save(teacherDto));
		return new ResponseDto();
//		return teacherService.save(teacherDto);
	}
	
	@PostMapping(TimApiPath.Teacher.UPLOAD_EXCEL)
	public ResponseDto uplaodExcelFile(@RequestPart("file") MultipartFile file) {
		return teacherService.save(file);
	}
	
	@GetMapping(TimApiPath.Teacher.GET)
	public ResponseEntity<ResponseDto> get(@RequestParam("userId") String userId){
		return ResponseEntity.ok(teacherService.findByUserId(userId));
	}
	
}