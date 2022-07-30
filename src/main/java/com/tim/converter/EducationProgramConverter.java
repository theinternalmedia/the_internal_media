package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.entity.EducationProgram;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class EducationProgramConverter extends AbstractConverter<EducationProgramDto, EducationProgram>{

	@Override
	public EducationProgramDto toDto(EducationProgram entity) {
		EducationProgramDto dto = this.modelMapper.map(entity, EducationProgramDto.class);
		return dto;
	}
	
	@Override
	public EducationProgram toEntity(EducationProgramDto dto) {
		// TODO Auto-generated method stub
		return this.modelMapper.map(dto, EducationProgram.class);
	}
}
