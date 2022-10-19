package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.FeedbackDto;
import com.tim.dto.educationprogram.EducationProgramDto;
import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.entity.EducationProgram;
import com.tim.entity.Faculty;
import com.tim.entity.Feedback;
import com.tim.entity.Notification;
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
