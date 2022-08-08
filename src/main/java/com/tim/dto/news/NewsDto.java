package com.tim.dto.news;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsDto extends BaseDto{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -1059857926078442605L;

}
