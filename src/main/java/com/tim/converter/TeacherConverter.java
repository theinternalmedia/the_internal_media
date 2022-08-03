package com.tim.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim.dto.teacher.TeacherDto;
import com.tim.entity.Teacher;

/**
 * 
 * @appName the_internal_media
 *
 */
@Component
public class TeacherConverter extends AbstractConverter<TeacherDto, Teacher> {

    /**
     * thinhnguyen
     */


    @Override
	public TeacherDto toDto(Teacher entity) {
        TeacherDto model = this.modelMapper.map(entity, TeacherDto.class);
        return model;
    }

    @Override
	public Teacher toEntity(TeacherDto dto) {
        return this.modelMapper.map(dto, Teacher.class);
    }
}
