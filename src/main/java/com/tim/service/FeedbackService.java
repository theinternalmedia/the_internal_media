package com.tim.service;

import java.util.List;

import com.tim.dto.FeedbackDto;

public interface FeedbackService {
	FeedbackDto create(FeedbackDto feedback);
	List<FeedbackDto> getAll();
}
