package com.tim.restful;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.converter.StudentConverter;
import com.tim.data.TimApiPath;
import com.tim.dto.student.StudentDto;
import com.tim.service.StudentService;
import com.tim.service.TeacherService;
import com.tim.utils.Utility;

@RestController
@RequestMapping(TimApiPath.TIM_API + "/test")
public class TestResource {

	@Autowired
	StudentService studentService;
	@Autowired
	private StudentConverter studentConverter;
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate; 
	
	@GetMapping("/notify")
	public void senNotification(@RequestParam String userId) {
//		messagingTemplate.convertAndSendToUser(userId, "/specific", "Xin chào" + userId);
		messagingTemplate.convertAndSend("/notification/" + userId, "Xin chào" + userId);
		messagingTemplate.convertAndSend("/notification/", "Xin chào" + userId);
	}

	@GetMapping("/findByChoolYearAndFacultyAndClass")
	public List<StudentDto> findByChoolYearAndFacultyAndClass(
			@RequestParam(value = "schooYearCodes", required = false) Set<String> schooYearCodes,
			@RequestParam(value = "facultyCodes", required = false) Set<String> facultyCodes,
			@RequestParam(value = "classCodes", required = false) Set<String> classCodes) {
		return studentConverter.toDtoList(studentService.findByChoolYearAndFacultyAndClass(schooYearCodes, facultyCodes, classCodes));
	}
	
	@GetMapping("/generate-slugs")
	public String generateSlugs(@RequestHeader(value="userId") String headerStr,
			@RequestHeader(value="password") String password,
			@RequestParam("string") String string) {
		System.out.print(string);
		return Utility.generateSlugs(string);
	}
}