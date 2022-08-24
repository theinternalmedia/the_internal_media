package com.tim.restful;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.converter.NewsConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.news.NewsDto;
import com.tim.repository.NewsRepository;
import com.tim.utils.ImageFileUploadUtil;
import com.tim.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private NewsRepository repo;
    @Autowired
    private NewsConverter converter;

    @PostMapping("/users/save")
    public ResponseDto saveUser(@RequestParam String newsRequestDtoJson,
                                @RequestPart("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        NewsDto newsDto = Utility.convertStringJsonToObject(newsRequestDtoJson, new TypeReference<NewsDto>(){});
        newsDto.setThumbnail(fileName);

        NewsDto savedUser = converter.toDto(repo.save(converter.toEntity(newsDto)));

        String uploadDir = "src/main/resources/user-photos/" + savedUser.getId();

        ImageFileUploadUtil.uploadFile(uploadDir, fileName, multipartFile);

        return new ResponseDto(savedUser);
    }
}
