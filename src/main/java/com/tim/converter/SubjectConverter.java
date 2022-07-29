package com.tim.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim.dto.SubjectDto;
import com.tim.entity.Subject;

@Component
public class SubjectConverter extends AbstractConverter<SubjectDto, Subject>{

	@Autowired
    private ModelMapper modelMapper;

	    SubjectDto toDto(Class Subject) {
	        SubjectDto subject = modelMapper.map(Subject, SubjectDto.class);
	        return subject;
	    }

	    Subject toEntity(SubjectDto subjectDto) {
	        return modelMapper.map(subjectDto, Subject.class);
	    }

}
