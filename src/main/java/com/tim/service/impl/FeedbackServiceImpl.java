package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tim.converter.FeedbackConverter;
import com.tim.dto.PageRequestDto;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.feedback.FeedbackDto;
import com.tim.entity.Feedback;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.FeedbackRepository;
import com.tim.service.FeedbackService;
import com.tim.utils.ValidationUtils;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	private static final String FEEDBACK = "Phản hồi";
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private FeedbackConverter feedbackConverter;

	@Override
	public FeedbackDto create(FeedbackDto dto) {
		// Validate input
		ValidationUtils.validateObject(dto);
		Feedback feedback = feedbackConverter.toEntity(dto);
		return feedbackConverter.toDto(feedbackRepository.save(feedback));
	}

	@Override
	public long toggleStatus(Set<Long> ids) {
		List<Feedback> feedbacks = new ArrayList<>();
		Feedback feedback;
		for(Long id: ids) {
			feedback = feedbackRepository.findById(id)
					.orElseThrow(() -> new TimNotFoundException(FEEDBACK, "ID", id.toString()));

			feedback.setStatus(!feedback.getStatus());
			feedbacks.add(feedback);
		}
		feedbackRepository.saveAll(feedbacks);
		return ids.size();
	}

	@Override
	public PagingResponseDto getPage(PageRequestDto pageRequestDto) {
		ValidationUtils.validateObject(pageRequestDto);
		Pageable pageable = PageRequest.of(
				pageRequestDto.getPage()-1, 
				pageRequestDto.getSize(),
				Sort.by("createdDate"));
		Page<Feedback> feedbackPage = feedbackRepository.findAll(pageable);
		List<Feedback> dataFeedbacks = feedbackPage.getContent();
		return new PagingResponseDto(
				feedbackPage.getTotalElements(),
				feedbackPage.getTotalPages(),
				feedbackPage.getNumber() + 1,
				feedbackPage.getSize(),
				dataFeedbacks);
	}


}
