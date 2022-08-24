package com.tim.dto.news;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.NewsAndNotifyDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewsDto extends NewsAndNotifyDto{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -1059857926078442605L;

	@NotBlank
	@Size(max = 150)
	private String title;

	@NotBlank
	@Size(max = 255)
	private String shortDescription;

	@NotBlank
	private String content;

}
