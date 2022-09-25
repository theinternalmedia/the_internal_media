package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.schoolyear.SchoolYearDto;
import com.tim.dto.schoolyear.SchoolYearCreateDto;
import com.tim.entity.SchoolYear;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class SchoolYearConverter extends AbstractConverter<SchoolYearDto, SchoolYear>{
	
	@Override
	public SchoolYearDto toDto(SchoolYear entity) {
		return this.modelMapper.map(entity, SchoolYearDto.class);
	}
	
	@Override
	public SchoolYear toEntity(SchoolYearDto dto) {
		return this.modelMapper.map(dto, SchoolYear.class);
	}

	public SchoolYear toEntity(SchoolYearCreateDto requestDto) {
		return this.modelMapper.map(requestDto, SchoolYear.class);
	}
	
	@Override
	public List<SchoolYearDto> toDtoList(List<SchoolYear> entities){
		List<SchoolYearDto> dtos = new ArrayList<>();
		entities.forEach(entity -> {
			dtos.add(toDto(entity));
		});
		return dtos;
	}
}
