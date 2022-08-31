package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.subject.SubjectDto;
import com.tim.dto.subject.SubjectRequestDto;
import com.tim.dto.subject.SubjectUpdateRequestDto;
import com.tim.entity.Subject;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class SubjectConverter extends AbstractConverter<SubjectDto, Subject> {

	@Override
	public SubjectDto toDto(Subject subjectEntity) {
		SubjectDto subject = this.modelMapper.map(subjectEntity, SubjectDto.class);
		return subject;
	}
	
	@Override
	public Subject toEntity(SubjectDto dto) {
		return this.modelMapper.map(dto, Subject.class);
	}

	public Subject toEntity(SubjectRequestDto requestDto) {
		return this.modelMapper.map(requestDto, Subject.class);
	}

	public Subject toEntity(SubjectUpdateRequestDto requestDto, Subject subject) {
		subject.setCode(requestDto.getCode());
		subject.setMandatory(requestDto.getMandatory());
		subject.setName(requestDto.getName());
		subject.setNumberOfCredits(requestDto.getNumberOfCredits());
		subject.setPassMarks(requestDto.getPassMarks());
		return subject;
	}
}
