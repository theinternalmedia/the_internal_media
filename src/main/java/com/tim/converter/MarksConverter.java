package com.tim.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim.dto.ScoresDto;
import com.tim.entity.Marks;

@Component
public class MarksConverter extends AbstractConverter<ScoresDto, Marks>{

	@Autowired
    private ModelMapper modelMapper;
	
	ScoresDto toDto(Marks scores) {
		ScoresDto scoresDto = modelMapper.map(scores, ScoresDto.class);
        return scoresDto;
    }


	Marks toEntity(ScoresDto subjectDto) {
        return modelMapper.map(subjectDto, Marks.class);
    }

}
