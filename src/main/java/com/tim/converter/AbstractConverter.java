package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.tim.dto.BaseDto;
import com.tim.entity.BaseEntity;

/**
 * convert between Entity and Dto
 * @appName the_internal_media
 *
 * @param <T1> must be extend from BaseDto
 * @param <T2> must be extend from BaseEntity
 */
public abstract class AbstractConverter<T1 extends BaseDto, T2 extends BaseEntity> {
	
	@Autowired
    protected ModelMapper modelMapper;
	
	/**
	 * convert from Entity to Dto
	 * @param entity an Entity
	 * @return an Dto after convert from an Entity
	 */
	abstract public T1 toDto(T2 entity);
	
	/**
	 * convert from EntityList to DtoList
	 * @param entityList an EntityList
	 * @return an DtoList after convert from an EntityList
	 */
	public List<T1> toDtoList(List<T2> entityList) {
		List<T1> result = new ArrayList<>();
		entityList.forEach(item ->{
			result.add(toDto(item));
		});
		return result;
	};
	
	/**
	 * convert from Dto to Entity
	 * @param dto an Dto
	 * @return an Entity after convert from an Dto
	 */
	public T2 toEntity(T1 dto) {
		return null;
	};
	
	/**
	 * convert from dtoList to entityList
	 * @param dtoList an DtoList
	 * @return an EntityList after convert from an dtoList
	 */
	public List<T2> toEntityList(List<T1> dtoList) {
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
