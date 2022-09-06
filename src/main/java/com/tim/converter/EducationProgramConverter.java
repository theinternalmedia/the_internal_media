package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramRequestDto;
import com.tim.dto.educationprogram.EducationProgramResponseDto;
import com.tim.dto.educationprogram.EducationProgramUpdateDto;
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
		return this.modelMapper.map(dto, EducationProgram.class);
	}
	
	/**
	 * 
	 * @author thinh
	 * @param requestDto
	 * @return EducationProgram
	 */
	public EducationProgram toEntity(EducationProgramRequestDto requestDto) {
		return this.modelMapper.map(requestDto, EducationProgram.class);
	}
	
	public EducationProgram toEntity(EducationProgramUpdateDto updateDto, EducationProgram entity) {
		entity.setCode(updateDto.getCode());
		entity.setName(updateDto.getName());
		return entity;	
	}

	public EducationProgramResponseDto toResponseDto(EducationProgram entity) {
		return this.modelMapper.map(entity, EducationProgramResponseDto.class);
	}
}
