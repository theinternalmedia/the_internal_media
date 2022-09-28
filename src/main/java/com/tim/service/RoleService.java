package com.tim.service;

import java.util.Set;

import com.tim.dto.role.RoleCreateDto;
import com.tim.dto.role.RoleDto;
import com.tim.dto.role.RoleUpdateDto;


public interface RoleService {
	RoleDto create(RoleCreateDto createDto);
	
	RoleDto update(RoleUpdateDto updateDto);
	
	long toggleStatus(Set<Long> ids);

	RoleDto getOneByCode(String roleCode);
}
