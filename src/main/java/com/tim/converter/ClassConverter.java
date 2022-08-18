package com.tim.converter;

import org.springframework.stereotype.Component;

import com.tim.dto.classz.ClassDto;
import com.tim.entity.Classz;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class ClassConverter extends AbstractConverter<ClassDto, Classz> {

    @Override
	public ClassDto toDto(Classz entity) {
    	ClassDto model = this.modelMapper.map(entity, ClassDto.class);
        return model;
    }

    @Override
	public Classz toEntity(ClassDto dto) {
        return this.modelMapper.map(dto, Classz.class);
    }
}
