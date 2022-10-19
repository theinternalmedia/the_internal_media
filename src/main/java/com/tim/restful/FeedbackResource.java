package com.tim.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.FeedbackDto;
import com.tim.service.FeedbackService;

@RestController
@RequestMapping(TimApiPath.TIM_API)
public class FeedbackResource {
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping(TimApiPath.Feedback.CREATE)
	public FeedbackDto create(@RequestBody FeedbackDto feedbackDto) {
		return feedbackService.create(feedbackDto);	
	}
	@GetMapping(TimApiPath.Feedback.GET_ALL)
	public List<FeedbackDto> getAll() {
		return feedbackService.getAll();	
	}
}
