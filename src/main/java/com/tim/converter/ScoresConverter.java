package com.tim.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim.dto.ScoresDto;
import com.tim.dto.SubjectDto;
import com.tim.entity.Scores;
import com.tim.entity.Subject;

@Component
public class ScoresConverter extends AbstractConverter<ScoresDto, Scores>{

	@Autowired
    private ModelMapper modelMapper;
	
	ScoresDto toDto(Scores scores) {
		ScoresDto scoresDto = modelMapper.map(scores, ScoresDto.class);
        return scoresDto;
    }


	Scores toEntity(ScoresDto subjectDto) {
        return modelMapper.map(subjectDto, Scores.class);
    }

}
