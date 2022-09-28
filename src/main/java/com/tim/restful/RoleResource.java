package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.role.RoleCreateDto;
import com.tim.dto.role.RoleDto;
import com.tim.dto.role.RoleUpdateDto;
import com.tim.service.RoleService;


@RestController
@RequestMapping(value = TimApiPath.TIM_API)
public class RoleResource {

	@Autowired
	RoleService roleService;
	
	
	@GetMapping(TimApiPath.Role.GET_BY_CODE)
	public RoleDto getOneByCode(@RequestParam String roleCode) {
		return roleService.getOneByCode(roleCode);
	}
	
	@PostMapping(TimApiPath.Role.CREATE)
	public RoleDto create(@RequestBody RoleCreateDto createDto) {
		return roleService.create(createDto);
	}
	
	@PutMapping(TimApiPath.Role.UPDATE)
	public RoleDto update(@RequestBody RoleUpdateDto updateDto) {
		return roleService.update(updateDto);
	}
	
	@PutMapping(TimApiPath.Role.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		return roleService.toggleStatus(ids);
	}
}
