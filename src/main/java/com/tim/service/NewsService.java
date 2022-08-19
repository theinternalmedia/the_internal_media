package com.tim.service;

import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;

public interface NewsService {

    ResponseDto create(NewsRequestDto requestDto);

    ResponseDto update(NewsDto newsDto);

    ResponseDto getOne(Long id);

    ResponseDto toogleStatus(Long id);

	ResponseDto getPage(int page, int size, boolean status, String facultyCode);

}
