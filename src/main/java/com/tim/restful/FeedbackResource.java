package com.tim.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.TimApiPath;
import com.tim.dto.PageRequestDto;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.feedback.FeedbackDto;
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
	
	@GetMapping(TimApiPath.Feedback.GET_PAGE)
	public PagingResponseDto getPage(PageRequestDto pageRequestDto) {
		return feedbackService.getPage(pageRequestDto);	
	}

	@PutMapping(TimApiPath.Feedback.TOGGLE_STATUS)
	public Long toggleStatus(@RequestParam Set<Long> ids) {
		System.out.println(ids);
		return feedbackService.toggleStatus(ids);
	}
}
