package com.tim.restful;

import java.util.Set;

import javax.websocket.server.PathParam;

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
import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassRequestDto;
import com.tim.dto.classz.ClassUpdateRequestDto;
import com.tim.service.ClassService;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class ClassResource {
	@Autowired
	private ClassService classService;
	
	@PostMapping(TimApiPath.Class.CREATE)
	public ClassDto create(@RequestBody ClassRequestDto requestDto) {
		return classService.create(requestDto);
	}
	
	@PostMapping(TimApiPath.Class.UPLOAD_EXCEL)
	public long uploadExcelFile(@RequestPart("file") MultipartFile file) {
		return classService.create(file);
	}
	
	@GetMapping(TimApiPath.Class.GET_BY_CODE)
	public ClassDto getByCode(@PathParam("code") String code) {
		return classService.getByCode(code);
	}
	
	@GetMapping(TimApiPath.Class.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPage(
									@RequestParam("page") int page,
									@RequestParam("size") int size,
									@RequestParam("status") boolean status,
									@PathParam("schoolYearCode") String schoolYearCode,
									@PathParam("facultyCode") String facultyCode,
									@PathParam("code") String code,
									@PathParam("name") String name){
		return ResponseEntity.ok(classService.getPaging(page, size, schoolYearCode,
								facultyCode, code, name, status));
	}
	
	@PutMapping(TimApiPath.Class.UPDATE)
	public ClassDto update(@RequestBody ClassUpdateRequestDto updateDto) {
		return classService.update(updateDto);
	}
	
	@PutMapping(TimApiPath.Class.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return classService.toggleStatus(ids);
	}
	
}
