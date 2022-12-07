package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.educationprogram.EducationProgramResponseDto;
import com.tim.dto.educationprogram.EducationProgramCreateDto;
import com.tim.dto.educationprogram.EduProgramAndSubjectResponseDto;
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
	
	public EducationProgramResponseDto toResponseDto(EducationProgram entity) {
		EducationProgramResponseDto dto = this.modelMapper.map(entity, EducationProgramResponseDto.class);
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
	public EducationProgram toEntity(EducationProgramCreateDto requestDto) {
		return this.modelMapper.map(requestDto, EducationProgram.class);
	}
	
	public EducationProgram toEntity(EducationProgramUpdateDto updateDto, EducationProgram entity) {
		entity.setCode(updateDto.getCode());
		entity.setName(updateDto.getName());
		return entity;	
	}

	public EduProgramAndSubjectResponseDto toResponseDtoAndSubject(EducationProgram entity) {
		return this.modelMapper.map(entity, EduProgramAndSubjectResponseDto.class);
	}
	
	@Override
	public List<EducationProgramDto> toDtoList(List<EducationProgram> entityList){
		List<EducationProgramDto> result = new ArrayList<>();
		entityList.forEach(entity -> result.add(
			toDto(entity)));
		return result;
	}
}
