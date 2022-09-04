package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;
import com.tim.entity.EducationProgramSubject;


@Component
public class EducationProgramSubjectConverter 
				extends AbstractConverter<EducationProgramSubjectDto, EducationProgramSubject>{

	@Override
	public EducationProgramSubject toEntity(EducationProgramSubjectDto dto) {
		return this.modelMapper.map(dto, EducationProgramSubject.class);
	}
	
	@Override
	public EducationProgramSubjectDto toDto(EducationProgramSubject entity) {
		return this.modelMapper.map(entity, EducationProgramSubjectDto.class);
	}
}
