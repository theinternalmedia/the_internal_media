package com.tim.service.impl;

import com.tim.converter.NewsConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.entity.Faculty;
import com.tim.entity.News;
import com.tim.repository.FacultyRepository;
import com.tim.repository.NewsRepository;
import com.tim.service.NewsService;
import com.tim.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsConverter newsConverter;
    @Autowired
    private FacultyRepository facultyRepository;


    @Override
    public ResponseDto create(/*@Valid*/ NewsDto newsDto) {
        List<Long> facultyIds = newsDto.getFacultyDtos();
        News entity = newsConverter.toEntity(newsDto);
        if (!facultyIds.isEmpty()) {
            Set<Faculty> faculties = new HashSet<>();
            facultyIds.forEach(id -> {
                Optional<Faculty> faculty = facultyRepository.findById(id);
                if ((faculty).isPresent()) {
                    faculties.add(faculty.get());
                }
            });
            entity.setFaculties(faculties);
        }
        return new ResponseDto(newsConverter.toDto(newsRepository.save(entity)));
    }

    @Override
    public ResponseDto update(NewsDto newsDto) {
        Long newsId = newsDto.getId();
        if (newsId != null) {
            News oldNews = newsRepository.getOneById(newsId);
            News newNews = newsConverter.toEntity(newsDto);
            oldNews.setContent(newNews.getContent());
            oldNews.setShortDescription(newNews.getShortDescription());
            oldNews.setThumbnail(newNews.getThumbnail());
            oldNews.setTitle(newNews.getTitle());
            return new ResponseDto(newsConverter.toDto(newsRepository.save(oldNews)));
        }
        return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
    }

    @Override
    public ResponseDto getOne(Long id) {
        News news = newsRepository.findByIdAndStatusTrue(id);
        if (news == null) {
            return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
                    TimConstants.ActualEntityName.NEWS,
                    "Id", id.toString()));
        }
        return new ResponseDto(newsConverter.toDto(news));
    }

    @Override
    public ResponseDto toogleStatus(Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            news.get().setStatus(false);
            newsRepository.save(news.get());
            return new ResponseDto();
        }
        return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
    }
}
