package com.tim.dto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class NewsRequestDto implements Serializable{
	
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -484888514160737597L;

	@NotBlank
	@Size(max = 150)
	private String title;

	@NotBlank
	@Size(max = 255)
	private String shortDescription;

	@NotBlank
	private String content;

	private MultipartFile thumbnailFile;

	private List<String> facultyCodes = new ArrayList<>();
}
