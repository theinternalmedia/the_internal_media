package com.tim.dto.news;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsUpdateDto extends NewsRequestDto{
	
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1342576901149328177L;
	
	@NotNull
	@Min(value = 1)
	private Long id;
}
