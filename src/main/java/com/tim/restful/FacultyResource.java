package com.tim.restful;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.Permissions;
import com.tim.data.TimApiPath;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyPageRequestDto;
import com.tim.dto.faculty.FacultyRequestDto;
import com.tim.dto.faculty.FacultyUpdateRequestDto;
import com.tim.service.FacultyService;


@RestController
@RequestMapping(TimApiPath.TIM_API)
public class FacultyResource {
	@Autowired
	private FacultyService facultyService;
	
	@PreAuthorize("hasAuthority('" + Permissions.Faculty.CREATE + "')")
	@PostMapping(TimApiPath.Faculty.CREATE)
	public FacultyDto create(@RequestBody FacultyRequestDto facultyDto) {
		return facultyService.create(facultyDto);
	}
	
	@GetMapping(TimApiPath.Faculty.GET_BY_CODE)
	public FacultyDto getByCode(@RequestParam("code") String code) {
		return facultyService.getByCode(code);
	}
	
	@GetMapping(TimApiPath.Faculty.GET_ALL)
	public List<FacultyDto> getAll(@RequestParam("status") boolean status){
		return facultyService.getAll(status);
	}
	
	@GetMapping(TimApiPath.Faculty.GET_PAGE)
	public ResponseEntity<PagingResponseDto> getPage(
							FacultyPageRequestDto pageRequestDto){
		return ResponseEntity.ok(facultyService.getPage(pageRequestDto));
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Faculty.UPDATE + "')")
	@PutMapping(TimApiPath.Faculty.UPDATE)
	public FacultyDto update(@RequestBody FacultyUpdateRequestDto updateDto) {
		return facultyService.update(updateDto);
	}
	
	@PreAuthorize("hasAuthority('" + Permissions.Faculty.TOGGLE_STATUS + "')")
	@PutMapping(TimApiPath.Faculty.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return facultyService.toggleStatus(ids);
	}
}
