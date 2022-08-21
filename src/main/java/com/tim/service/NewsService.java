package com.tim.service;

import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;

public interface NewsService {

	/**
	 * Create News.
	 * 
	 * @author thinh
	 * @param requestDto
	 * @return ResponseDto
	 */
    ResponseDto create(NewsRequestDto requestDto);

    /**
     * Update News.
     * 
     * @author thinh
     * @param newsDto
     * @return ResponseDto
     */
    ResponseDto update(NewsDto newsDto);

    /**
     * @author minhtuanitk43
     * @param id
     * @return
     */
    ResponseDto getOne(Long id);

    /**
     * Toogle status.
     * 
     * @author thinh
     * @param id
     * @return ResponseDto
     */
    ResponseDto toogleStatus(Long id);
    
    /**
     * Get news page.
     * 
     * @author minhtuanitk43
     * @param page
     * @param size
     * @param status
     * @param facultyCode
     * @return
     */
	PagingResponseDto getPage(int page, int size, boolean status, String facultyCode);

}
