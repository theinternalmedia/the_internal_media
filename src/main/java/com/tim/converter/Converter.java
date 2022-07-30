package com.tim.converter;

import com.tim.dto.BaseDto;
import com.tim.entity.BaseEntity;

/**
 * convert between Entity and Dto
 * @appName the_internal_media
 *
 * @param <T1>
 * @param <T2>
 */
public abstract class Converter<T1 extends BaseDto, T2 extends BaseEntity> {
	
	/**
	 * convert from Entity to Dto
	 * @param entity
	 * @return an Dto class after convert from an Entity
	 */
	public T1 toDto(T2 entity) {
		return null;
	};
	
	/**
	 * convert from Dto to Entity
	 * @param dto
	 * @return an Entity class after convert from an Dto
	 */
	public T2 toEntity(T1 dto) {
		return null;
	};
	
	/**
	 * convert from Dto to Entity
	 * @param dto get from request and update fields to Entity
	 * @param oldEntity get from database and update fields is need to change
	 * @return an Entity after update oldEntity from Dto
	 */
	public T2 toEntity(T1 dto, T2 oldEntity) {
		return null;
	};
}
