package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.marks.MarksDto;
import com.tim.dto.marks.MarksRequestDto;
import com.tim.entity.Marks;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class MarksConverter extends AbstractConverter<MarksDto, Marks>{

	@Override
	public MarksDto toDto(Marks scores) {
		MarksDto scoresDto = this.modelMapper.map(scores, MarksDto.class);
        return scoresDto;
    }

	@Override
	public Marks toEntity(MarksDto subjectDto) {
        return this.modelMapper.map(subjectDto, Marks.class);
    }

	public Marks toEntity(MarksRequestDto requestDto) {
		return this.modelMapper.map(requestDto, Marks.class);
	}

}
