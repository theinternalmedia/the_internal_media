package com.tim.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.StudentConverter;
import com.tim.converter.TeacherConverter;
import com.tim.data.TimApiPath;
import com.tim.data.TimConstants;
import com.tim.dto.PasswordDto;
import com.tim.dto.UserUpdateRequestDto;
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
	
	@Autowired
	private StudentConverter studentConverter;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	@PutMapping(TimApiPath.User.UPDATE_AVATAR)
	public String uploadAvatar(@RequestPart("avatar") MultipartFile avatar){
		if(PrincipalUtils.authenticatedUserIsTeacher()) {
			return teacherService.updateAvatar(avatar);
		} else {
			return studentService.updateAvatar(avatar);
		}
	}
	
	@PutMapping(TimApiPath.User.UPDATE_USER)
	public ResponseEntity<?> updateProfile(@RequestBody UserUpdateRequestDto userDto) {
		//Guaranteed student, teacher can't change UserId
		String UserId = PrincipalUtils.getAuthenticatedUsersUserId();
		userDto.setUserId(UserId);
		
		if(PrincipalUtils.authenticatedUserIsTeacher()) {
			return ResponseEntity.ok(teacherService.update(teacherConverter.toDto(userDto)));
		}else {
			return ResponseEntity.ok(studentService.update(studentConverter.toDto(userDto)));
		}
	}
	
	@GetMapping(TimApiPath.User.GET_BY_USERID)
	public ResponseEntity<?> getByUserId(){
		String userId = PrincipalUtils.getAuthenticatedUsersUserId();
		if(PrincipalUtils.authenticatedUserIsTeacher()) {
			return ResponseEntity.ok(teacherService.getByUserId(userId));
		}else {
			return ResponseEntity.ok(studentService.getByUserId(userId));
		}
	}
	
	@PutMapping(TimApiPath.User.UPDATE_PASSWORD)
	public ResponseEntity<String> updatePassword(@RequestBody PasswordDto passwordDto){
		if(PrincipalUtils.authenticatedUserIsTeacher()) {
			teacherService.updatePassword(passwordDto);
		}else {
			studentService.updatePassword(passwordDto);
		}
		return ResponseEntity.ok(TimConstants.SUCCESS); 
	}
}
