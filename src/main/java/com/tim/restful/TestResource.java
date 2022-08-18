package com.tim.restful;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.converter.StudentConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimApiPath;
import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentDto;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import com.tim.service.TeacherService;
import com.tim.utils.Utility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API + "/test")
public class TestResource {

	@Autowired
	StudentService studentService;
	@Autowired
	private StudentConverter studentConverter;
	@Autowired
	private TeacherService teacherService;

	@GetMapping("/message")
	public String testGetExceptionMessage() {
		return Utility.getMessage(ETimMessages.INTERNAL_SYSTEM_ERROR);
	}

	@GetMapping("/findByChoolYearAndFacultyAndClass")
	public List<StudentDto> findByChoolYearAndFacultyAndClass(
			@RequestParam(value = "schooYearCodes", required = false) Set<String> schooYearCodes,
			@RequestParam(value = "facultyCodes", required = false) Set<String> facultyCodes,
			@RequestParam(value = "classCodes", required = false) Set<String> classCodes) {
		return studentConverter.toDtoList(studentService.findByChoolYearAndFacultyAndClass(schooYearCodes, facultyCodes, classCodes));
	}
	
	@GetMapping("/listStudentInUserIds")
	public ResponseDto listStudentInUserIds(@RequestParam("userIds") List<String> userIds) {
		return new ResponseDto();
	}
	
	@GetMapping("/exportStudents")
	public ResponseDto exportStudents() {
		studentService.exportToExcelFile();
		return new ResponseDto();
	}
	
	@GetMapping("/exportTeachers")
	public ResponseDto exportTeachers() {
		teacherService.exportToExcelFile();
		return new ResponseDto();
	}
}