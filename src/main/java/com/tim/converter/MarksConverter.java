package com.tim.converter;

import com.tim.dto.marks.MarksDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim.entity.Marks;

@Component
public class MarksConverter extends AbstractConverter<MarksDto, Marks>{

	@Autowired
    private ModelMapper modelMapper;
	
	MarksDto toDto(Marks scores) {
		MarksDto scoresDto = modelMapper.map(scores, MarksDto.class);
        return scoresDto;
    }


	Marks toEntity(MarksDto subjectDto) {
        return modelMapper.map(subjectDto, Marks.class);
    }

}
