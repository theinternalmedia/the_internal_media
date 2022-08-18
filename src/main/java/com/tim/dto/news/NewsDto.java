package com.tim.dto.news;

import com.tim.dto.BaseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class NewsDto extends BaseDto{

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

	private String thumbnail;

	private List<Long> facultyDtos = new ArrayList<>();

}
