package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.TimApiPath;
import com.tim.service.StudentService;
import com.tim.service.TeacherService;
import com.tim.utils.PrincipalUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class UserResource {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	@PutMapping(TimApiPath.User.UPDATE_AVATAR)
	public String uploadAvatar(@RequestPart("avatar") MultipartFile avatar){
		if(PrincipalUtils.authenticatedUserIsTeacher()) {
			return teacherService.updateAvatar(avatar);
		} else {
			return studentService.updateAvatar(avatar);
		}
	}
}
