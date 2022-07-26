package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.Permissions;
import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassPageRequestDto;
import com.tim.dto.classz.ClassCreateDto;
import com.tim.dto.classz.ClassUpdateDto;
import com.tim.service.ClassService;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class ClassResource {
	@Autowired
	private ClassService classService;
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.CREATE + "')")
	@PostMapping(TimApiPath.Class.CREATE)
	public ClassDto create(@RequestBody ClassCreateDto requestDto) {
		return classService.create(requestDto);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.CREATE + "')")
	@PostMapping(TimApiPath.Class.UPLOAD_EXCEL)
	public long uploadExcelFile(@RequestPart("file") MultipartFile file) {
		return classService.create(file);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.READ + "')")
	@GetMapping(TimApiPath.Class.GET_BY_CODE)
	public ClassDto getByCode(@RequestParam("code") String code) {
		return classService.getByCode(code);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.READ + "')")
	@GetMapping(TimApiPath.Class.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPage(
			ClassPageRequestDto pageRequestDto){
		return ResponseEntity.ok(classService.getPaging(pageRequestDto));
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Classz.UPDATE + "')")
	@PutMapping(TimApiPath.Class.UPDATE)
	public ClassDto update(@RequestBody ClassUpdateDto updateDto) {
		return classService.update(updateDto);
	}

	@PreAuthorize("hasAuthority('" + Permissions.Classz.TOGGLE_STATUS + "')")
	@PutMapping(TimApiPath.Class.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return classService.toggleStatus(ids);
	}
	
}
