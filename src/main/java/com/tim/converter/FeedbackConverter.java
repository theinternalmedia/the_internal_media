package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.feedback.FeedbackDto;
import com.tim.entity.Feedback;

@Component
public class FeedbackConverter extends AbstractConverter<FeedbackDto, Feedback> {

	@Override
	public FeedbackDto toDto(Feedback entity) {
		return modelMapper.map(entity, FeedbackDto.class);
	}
	@Override
	public Feedback toEntity(FeedbackDto dto) {
		return this.modelMapper.map(dto, Feedback.class);
	}
	@Override
	public List<FeedbackDto> toDtoList(List<Feedback> entities){
		List<FeedbackDto> dtos = new ArrayList<>();
		entities.forEach(entity -> {
			dtos.add(toDto(entity));
		});
		return dtos;
	}
}
