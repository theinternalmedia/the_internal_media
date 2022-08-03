package com.tim.restful;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.tim.dto.teacher.TeacherDto;
import com.tim.service.TeacherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.Teacher.PREFIX)
public class TeacherResource extends AbstractResource {
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(TimApiPath.Teacher.SAVE)
	public String save(@Valid @RequestBody TeacherDto teacherDto) {
		return String.valueOf(teacherService.save(teacherDto));
	}
	
	@PostMapping(TimApiPath.Teacher.UPLOAD_EXCEL)
	public List<?> uplaodExcelFile(@RequestPart("file") MultipartFile file) {
		return teacherService.importExcelFile(file);
	}
	
	@GetMapping(TimApiPath.Teacher.GET)
	public TeacherDto get(@RequestParam("userId") String userId) {
		return teacherService.findByUserId(userId);
	}
	
}