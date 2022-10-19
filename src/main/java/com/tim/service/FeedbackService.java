package com.tim.service;

import java.util.List;
import java.util.Set;

import com.tim.dto.PageRequestDto;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.feedback.FeedbackDto;

public interface FeedbackService {
	FeedbackDto create(FeedbackDto feedback);
	PagingResponseDto getPage(PageRequestDto pageRequestDto);
	long toggleStatus(Set<Long> ids);
}
