package com.tim.restful;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.tim.dto.ResponseDto;
import com.tim.dto.teacher.TeacherRequestDto;
import com.tim.dto.teacher.TeacherUpdateRequestDto;
import com.tim.service.TeacherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class TeacherResource {
	
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(TimApiPath.Teacher.CREATE)
	public ResponseDto create(@RequestBody TeacherRequestDto requestDto) {
		return teacherService.create(requestDto);
	}
	
	@PutMapping(TimApiPath.Teacher.UPDATE)
	public ResponseDto update(@RequestBody TeacherUpdateRequestDto requestDto) {
		return teacherService.update(requestDto);
	}
	
	@PostMapping(TimApiPath.Teacher.UPLOAD_EXCEL)
	public ResponseDto uplaodExcelFile(@RequestPart("file") MultipartFile file) {
		return teacherService.create(file);
	}
	
	@GetMapping(TimApiPath.Teacher.GET_BY_USERID)
	public ResponseEntity<ResponseDto> getByUserId(@RequestParam("userId") String userId){
		return ResponseEntity.ok(teacherService.getOne(userId));
	}

	@PutMapping(TimApiPath.Teacher.UPLOAD_IMAGE)
	public ResponseDto uploadAvatar(@RequestPart("avatar") MultipartFile avatar,
									@RequestParam("userId") String userId){
		return teacherService.updateAvatar(avatar, userId);
	}
	
	@GetMapping(TimApiPath.Teacher.GET_PAGE)
	public ResponseEntity<ResponseDto> getPage(
			@PathParam("facultyCode") String facultyCode,
			@PathParam("name") String name, 
			@PathParam("userId") String userId,
			@RequestParam("page") int page,
			@RequestParam("size") int size){
		return ResponseEntity.ok(teacherService.getPage(facultyCode, name, userId, page, size));
	}
	
	@PutMapping(TimApiPath.Teacher.TOGGLE_STATUS)
	public ResponseDto toggleStatus(@PathParam("id") Long id) {
		return teacherService.toggleStatus(id);
	}
}