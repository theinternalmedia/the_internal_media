package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.schoolyear.SchoolYearDto;
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
}
