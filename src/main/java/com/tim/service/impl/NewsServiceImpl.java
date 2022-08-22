package com.tim.service.impl;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.utils.ImageFileUploadUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
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
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.entity.Faculty;
import com.tim.entity.News;
import com.tim.repository.FacultyRepository;
import com.tim.repository.NewsRepository;
import com.tim.service.NewsService;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsConverter newsConverter;
    @Autowired
    private FacultyRepository facultyRepository;


    @Override
    @Transactional
    public ResponseDto create(String newsDtoJsonRequest, MultipartFile image) {
        NewsRequestDto newsRequestDto = Utility.convertStringJsonToObject(newsDtoJsonRequest,
                                                        new TypeReference<NewsRequestDto>() {});
    	ValidationUtils.validateObject(newsRequestDto);
        ValidationUtils.validateImage(image);

        News entity = newsConverter.toEntity(newsRequestDto);

        List<String> facultyCodes = newsRequestDto.getFacultyCodes();
        if (CollectionUtils.isNotEmpty(facultyCodes)) {
            Set<Faculty> faculties = new HashSet<>();
            faculties = facultyRepository.findByCodeIn(facultyCodes);
            entity.setFaculties(faculties);
        }

        //save file to resources
        String fileName = ImageFileUploadUtil.createFileName(image, TimConstants.Upload.NEWS_PREFIX);
        try{
            ImageFileUploadUtil.saveFile(TimConstants.Upload.THUMBNAIL_UPLOAD_DIR, fileName, image);
        }catch(IOException e){
            return new ResponseDto(TimConstants.Upload.SAVE_UNSUCCESS);
        }

        //set imagePath to thumbnail of newsEntity and save to db
        String newsImagePath = TimConstants.Upload.THUMBNAIL_PATH + fileName;
        entity.setThumbnail(newsImagePath);
        NewsDto savedUser = newsConverter.toDto(newsRepository.save(entity));

        return new ResponseDto(savedUser);
    }


    @Override
    public ResponseDto update(NewsDto newsDto) {
    	// Validate Object
    	ValidationUtils.validateObject(newsDto);
    	
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

	@Override
	public ResponseDto getPage(int page, int size, boolean status, String facultyCode) {
		TimSpecification<News> timSpecification = new TimSpecification<News>();
		timSpecification.add(new SearchCriteria("status", status, SearchOperation.EQUAL));
		Specification<News> specification = Specification.where(
				(root, query, builder) -> {
					return builder.equal(root.get("status"), status);
				});
		if(StringUtils.isNotBlank(facultyCode)) {
			specification = specification.and((root, query, builder) -> {
				return builder.equal(root.join("faculties").get("code"), facultyCode);
			});
		}
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate"));
		Page<News> pageTeachers = newsRepository.findAll(specification, pageable);
		List<NewsDto> data = newsConverter.toDtoList(pageTeachers.getContent());
		return new PagingResponseDto(pageTeachers.getTotalElements(), 
				pageTeachers.getTotalPages(),
				pageTeachers.getNumber() + 1, 
				pageTeachers.getSize(), 
				data);
	}
}
