package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyRequestDto;
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

	public Faculty toEntity(FacultyRequestDto dto) {
		return this.modelMapper.map(dto, Faculty.class);
	}
}
