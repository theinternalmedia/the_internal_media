package com.tim.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.NewsConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.dto.news.NewsUpdateDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.entity.Faculty;
import com.tim.entity.News;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.FacultyRepository;
import com.tim.repository.NewsRepository;
import com.tim.service.NewsService;
import com.tim.utils.UploadUtils;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;

@Service
public class NewsServiceImpl implements NewsService {
	private static final String NEWS = "Tin Tức";

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsConverter newsConverter;
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    @Transactional
    public NewsDto create(NewsRequestDto requestDto, MultipartFile image) {
        // Validate Object
        ValidationUtils.validateObject(requestDto);

        List<String> facultyCodes = requestDto.getFacultyCodes();
        News entity = newsConverter.toEntity(requestDto);
        
        // Map Faculties
        Set<Faculty> faculties = new HashSet<>();
        for (String facultyCode : facultyCodes) {
        	Faculty faculty = facultyRepository.getByCode(facultyCode).orElseThrow(() -> 
        			new TimNotFoundException(NEWS, "Mã", facultyCode));
        	faculties.add(faculty);
        }
        entity.setFaculties(faculties);
        
        // Save thumbnail
        if (image != null) {
        	final String fileName = Utility.getFileNameFromTime(TimConstants.Upload.NEWS_PREFIX);
            try {
                String thumnbnail = UploadUtils.uploadImage(image, TimConstants.Upload.THUMBNAIL_DIR, fileName);
                entity.setThumbnail(thumnbnail);
            }catch (IOException e){
                throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
            }
        }
        // Set Slug
        entity.setSlug(Utility.generateSlugs(entity.getTitle()));
        entity = newsRepository.save(entity);
        return newsConverter.toDto(entity);
    }

    @Override
    @Transactional
    public NewsDto update(NewsUpdateDto requestDto, MultipartFile image) {
        // Validate Object
        ValidationUtils.validateObject(requestDto);

        Long newsId = requestDto.getId();
        News oldNews = newsRepository.findById(newsId).orElseThrow(() -> 
        	new TimNotFoundException(NEWS, "ID", newsId.toString()));
        oldNews = newsConverter.toEntity(requestDto, oldNews);
        if (image != null) {
        	final String fileName = Utility.getFileNameFromTime(TimConstants.Upload.NEWS_PREFIX);
            try {
            	if (StringUtils.isNotBlank(oldNews.getThumbnail())) {
            		UploadUtils.delelteFile(oldNews.getThumbnail());
                }
                String thumnbnail = UploadUtils.uploadImage(image, 
                		TimConstants.Upload.THUMBNAIL_DIR, fileName);
                oldNews.setThumbnail(thumnbnail);
            }catch (IOException e){
                throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
            }
        }
        List<String> facultyCodes = requestDto.getFacultyCodes();

        // Clear faculties
        oldNews.getFaculties().clear();
        
        // Map Faculties
        Set<Faculty> faculties = new HashSet<>();
        for (String facultyCode : facultyCodes) {
        	Faculty faculty = facultyRepository.getByCode(facultyCode).orElseThrow(() -> 
        			new TimNotFoundException(NEWS, "Mã", facultyCode));
        	faculties.add(faculty);
        }
        oldNews.setFaculties(faculties);

        // Set Slug
        oldNews.setSlug(Utility.generateSlugs(oldNews.getTitle()));
        oldNews = newsRepository.save(oldNews);

        return newsConverter.toDto(oldNews);
    }

    @Override
    public NewsDto getOne(Long id) {
        News news = newsRepository.findByIdAndStatusTrue(id).orElseThrow(() -> 
        	new TimNotFoundException(NEWS, "ID", String.valueOf(id)));
        return newsConverter.toDto(news);
    }

    @Override
    public Long toogleStatus(Long id) {
        News news = newsRepository.findById(id).orElseThrow(() -> 
        	new TimNotFoundException(NEWS, "ID", String.valueOf(id)));
        news.setStatus(!news.getStatus());
        newsRepository.save(news);
        return id;
    }

    @Override
    public PagingResponseDto getPage(int page, int size, boolean status,
                                     Long id, String search, String facultyCode) {
        TimSpecification<News> timSpecification = new TimSpecification<News>();
        timSpecification.add(new SearchCriteria("status", status, SearchOperation.EQUAL));

        // Id not null
        if (id != null) {
            timSpecification.add(new SearchCriteria("id", id, SearchOperation.EQUAL));
        }

        Specification<News> specification = Specification.where(null);

        // Search not null
        if (StringUtils.isNotBlank(search)) {
            specification = specification.and((root, query, builder) -> {
                return builder.like(builder.lower(
                        root.get("title")), "%" + search.toLowerCase() + "%");
            }).or((root, query, builder) -> {
                return builder.like(builder.lower(
                        root.get("shortDescription")), "%" + search.toLowerCase() + "%");
            }).or((root, query, builder) -> {
                return builder.like(builder.lower(
                        root.get("content")), "%" + search.toLowerCase() + "%");
            });
        }

        // FacultyCode not null
        if (StringUtils.isNotBlank(facultyCode)) {
            specification = specification.and((root, query, builder) -> {
                return builder.equal(root.join("faculties").get("code"), facultyCode);
            });
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate"));
        Page<News> pageTeachers = newsRepository.findAll(specification.and(timSpecification), pageable);
        List<NewsDto> data = newsConverter.toDtoList(pageTeachers.getContent());
        return new PagingResponseDto(pageTeachers.getTotalElements(),
                pageTeachers.getTotalPages(),
                pageTeachers.getNumber() + 1,
                pageTeachers.getSize(),
                data);
    }

    @Override
    public NewsDto getOne(String slug) {
        News news = newsRepository.findBySlug(slug).orElseThrow(() -> 
        	new TimNotFoundException(NEWS, "Slug", slug));
        return newsConverter.toDto(news);
    }
}
