package com.tim.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassCreateDto;
import com.tim.dto.classz.ClassUpdateDto;
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

	public Classz toEntity(ClassCreateDto requestDto) {
		return this.modelMapper.map(requestDto, Classz.class);
	}
	
	@Override
	public List<ClassDto> toDtoList(List<Classz> entityList){
		List<ClassDto> result = new ArrayList<>();
		entityList.forEach(entity -> result.add(
			toDto(entity)));
		return result;
	}
	
	public Classz toEntity(ClassUpdateDto dto, Classz entity) {
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		return entity;
	}
}
