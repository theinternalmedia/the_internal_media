package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.classes.ClassDto;
import com.tim.entity.Class;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class ClassConverter extends AbstractConverter<ClassDto, Class> {

    @Override
	public ClassDto toDto(Class entity) {
    	ClassDto model = this.modelMapper.map(entity, ClassDto.class);
        return model;
    }

    @Override
	public Class toEntity(ClassDto dto) {
        return this.modelMapper.map(dto, Class.class);
    }
}
