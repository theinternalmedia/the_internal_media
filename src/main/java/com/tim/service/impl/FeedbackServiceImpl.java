package com.tim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.FeedbackConverter;
import com.tim.dto.FeedbackDto;
import com.tim.entity.Feedback;
import com.tim.repository.FeedbackRepository;
import com.tim.service.FeedbackService;
import com.tim.utils.ValidationUtils;
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private FeedbackConverter feedbackConverter;

	@Override
	public FeedbackDto create(FeedbackDto dto) {
		// TODO Auto-generated method stub
		ValidationUtils.validateObject(dto);
		Feedback feedback = feedbackConverter.toEntity(dto);
		return feedbackConverter.toDto(feedbackRepository.save(feedback));
	}

	@Override
	public List<FeedbackDto> getAll() {
		List<Feedback> feedback = feedbackRepository.findAll();
		return feedbackConverter.toDtoList(feedback);
	}

}
