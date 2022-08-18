package com.tim.converter;

import com.tim.dto.news.NewsDto;
import com.tim.entity.News;
import org.springframework.stereotype.Component;

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
}
