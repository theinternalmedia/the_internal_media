package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.entity.News;

@Component
public class NewsConverter extends AbstractConverter<NewsDto, News> {

    @Override
    public News toEntity(NewsDto newsDto){
        return this.modelMapper.map(newsDto, News.class);
    }

    @Override
    public NewsDto toDto(News entity){
        return this.modelMapper.map(entity, NewsDto.class);
    }

    /**
     * @author minhtuanitk43
     * @param requestDto
     * @return News Entity
     */
	public News toEntity(NewsRequestDto requestDto) {
		return this.modelMapper.map(requestDto, News.class);
	}
	
	@Override
	public List<NewsDto> toDtoList(List<News> entityList) {
		List<NewsDto> result = new ArrayList<>();
		entityList.forEach(item -> {
			result.add(toDto(item));
		});
		return result;
	}
}
