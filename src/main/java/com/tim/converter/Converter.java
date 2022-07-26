package com.tim.converter;

import com.tim.dto.BaseDto;
import com.tim.entity.BaseEntity;

public interface Converter<T1 extends BaseDto, T2 extends BaseEntity> {
	T1 toDto(T2 entity);
	T2 toEntity(T1 dto);
	T2 toEntity(T1 dto, T2 oldEntity);
}
