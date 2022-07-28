package com.tim.converter;

import com.tim.entity.Teacher;
import com.tim.model.TeacherModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter extends AbstractConverter<TeacherModel, Teacher> {

    /**
     * thinhnguyen
     */

    @Autowired
    private ModelMapper modelMapper;

    @Override
    TeacherModel toDto(Teacher entity) {
        TeacherModel model = modelMapper.map(entity, TeacherModel.class);
        return model;
    }

    @Override
    Teacher toEntity(TeacherModel dto) {
        return modelMapper.map(dto, Teacher.class);
    }
}
