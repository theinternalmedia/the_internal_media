package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.educationprogramsubject.EducationProgramSubjectCreateDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectDto;
import com.tim.dto.educationprogramsubject.EducationProgramSubjectResponseDto;
import com.tim.entity.EducationProgramSubject;
import com.tim.entity.Subject;


@Component
public class EducationProgramSubjectConverter 
				extends AbstractConverter<EducationProgramSubjectDto, EducationProgramSubject>{

	
	@Override
	public EducationProgramSubjectDto toDto(EducationProgramSubject entity) {
		return this.modelMapper.map(entity, EducationProgramSubjectDto.class);
	}
	
	public EducationProgramSubject toEntity(EducationProgramSubjectCreateDto dto) {
		return this.modelMapper.map(dto, EducationProgramSubject.class);
	}

	public EducationProgramSubjectResponseDto toResponseDto(EducationProgramSubject entity) {
		EducationProgramSubjectResponseDto response = new EducationProgramSubjectResponseDto();
		response.setNote(entity.getNote());
		response.setSemester(entity.getSemester());
		Subject subject = entity.getSubject();
		response.setSubjectCode(subject.getCode());
		response.setSubjectName(subject.getName());
		response.setMandatory(subject.isMandatory());
		response.setNumberOfCredits(subject.getNumberOfCredits());
		return response;
	}
}
