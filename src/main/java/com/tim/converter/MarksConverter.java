package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.marks.MarksCreateDto;
import com.tim.dto.marks.MarksDto;
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

	public Marks toEntity(MarksCreateDto requestDto) {
		return this.modelMapper.map(requestDto, Marks.class);
	}
	
	@Override
	public List<MarksDto> toDtoList(List<Marks> marks){
		List<MarksDto> result = new ArrayList<>();
		MarksDto dto = new MarksDto();
		for(Marks item : marks) {
			dto = toDto(item);
			dto.setStudentName(item.getStudent().getName());
			dto.setStudentUserId(item.getStudent().getUserId());
			result.add(dto);
		}
		return result;
	}

}
