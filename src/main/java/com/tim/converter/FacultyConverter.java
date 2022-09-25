package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyCreateDto;
import com.tim.dto.faculty.FacultyUpdateDto;
import com.tim.entity.Faculty;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class FacultyConverter extends AbstractConverter<FacultyDto, Faculty> {

	@Override
	public FacultyDto toDto(Faculty entity) {
		// TODO Auto-generated method stub
		return this.modelMapper.map(entity, FacultyDto.class);
	}

	@Override
	public Faculty toEntity(FacultyDto dto) {
		// TODO Auto-generated method stub
		return this.modelMapper.map(dto, Faculty.class);
	}
	
	public Faculty toEntity(FacultyUpdateDto updateDto, Faculty entity) {
		entity.setCode(updateDto.getCode());
		entity.setName(updateDto.getName());
		entity.setId(updateDto.getId());
		return entity;
	}
	
	@Override
	public List<FacultyDto> toDtoList(List<Faculty> entities){
		List<FacultyDto> dtos = new ArrayList<>();
		entities.forEach(entity -> {
			dtos.add(toDto(entity));
		});
		return dtos;
	}

	public Faculty toEntity(FacultyCreateDto dto) {
		return this.modelMapper.map(dto, Faculty.class);
	}
}
