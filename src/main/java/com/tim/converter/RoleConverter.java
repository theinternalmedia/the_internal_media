package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.role.RoleCreateDto;
import com.tim.dto.role.RoleDto;
import com.tim.entity.Role;


@Component
public class RoleConverter extends AbstractConverter<RoleDto, Role>{
	
	@Override
	public RoleDto toDto(Role entity) {
		return modelMapper.map(entity, RoleDto.class); 
	}
	
	@Override
	public Role toEntity(RoleDto dto) {
		return modelMapper.map(dto, Role.class);
	}
	
	public Role toEntity(RoleCreateDto createDto) {
		return modelMapper.map(createDto, Role.class);
	}
}
