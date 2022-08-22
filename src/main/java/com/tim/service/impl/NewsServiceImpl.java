package com.tim.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tim.converter.NewsConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.dto.news.NewsRequestDto;
import com.tim.dto.news.NewsUpdateDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.entity.Faculty;
import com.tim.entity.News;
import com.tim.repository.FacultyRepository;
import com.tim.repository.NewsRepository;
import com.tim.service.NewsService;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsConverter newsConverter;
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public ResponseDto create(NewsRequestDto requestDto) {
    	// Validate Object
    	ValidationUtils.validateObject(requestDto);
    	
        List<String> facultyCodes = requestDto.getFacultyCodes();
        News entity = newsConverter.toEntity(requestDto);
        if (CollectionUtils.isNotEmpty(facultyCodes)) {
            Set<Faculty> faculties = new HashSet<>();
            faculties = facultyRepository.findByCodeIn(facultyCodes);
            entity.setFaculties(faculties);
        }
        if (requestDto.getThumbnailFile() != null) {
        	// upload thumbnail
        	entity.setThumbnail(null);
        }
        // Set Slug
        entity.setSlug(Utility.generateSlugs(entity.getTitle()));
        entity = newsRepository.save(entity);
        return new ResponseDto(newsConverter.toDto(entity));
    }

    @Override
    public ResponseDto update(NewsUpdateDto requestDto) {
    	// Validate Object
    	ValidationUtils.validateObject(requestDto);
    	
        Long newsId = requestDto.getId(); 
        if (newsId != null) {
            News oldNews = newsRepository.findById(newsId).orElse(null);
            if (oldNews != null)
            oldNews = newsConverter.toEntity(requestDto, oldNews);
            if (requestDto.getThumbnailFile() != null) {
            	// upload thumbnail file:
            		// Kiểm tra định dạng -> throw Exception
            		// Kiểm tra nếu tồn tại thì xóa xong ms save
            		// Kiểm tra save thành công -> -> throw Exception
            	 oldNews.setThumbnail(null);
            }
            List<String> facultyCodes = requestDto.getFacultyCodes();
            
            // Clear faculties
            oldNews.getFaculties().clear();
            if (CollectionUtils.isNotEmpty(facultyCodes)) {
                Set<Faculty> faculties = new HashSet<>();
                faculties = facultyRepository.findByCodeIn(facultyCodes);
                oldNews.setFaculties(faculties);
            }
            
            // Set Slug
            oldNews.setSlug(Utility.generateSlugs(oldNews.getTitle()));
            oldNews = newsRepository.save(oldNews);
            return new ResponseDto(newsConverter.toDto(oldNews));
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
        	News entity = news.get();
            newsRepository.save(entity);
            return new ResponseDto();
        }
        return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
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
	public ResponseDto getOne(String slug) {
		Optional<News> news = newsRepository.findBySlug(slug);
        if (news.isEmpty()) {
            return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
                    TimConstants.ActualEntityName.NEWS,
                    "Slug", slug));
        }
        return new ResponseDto(newsConverter.toDto(news.get()));
	}
}
