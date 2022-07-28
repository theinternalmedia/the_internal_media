package com.tim.converter;

import com.tim.entity.Class;
import com.tim.model.ClassModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassConverter extends AbstractConverter<ClassModel, Class> {

    /**
     * thinhnguyen
     */

    @Autowired
    private ModelMapper modelMapper;

    @Override
    ClassModel toDto(Class entity) {
        ClassModel model = modelMapper.map(entity, ClassModel.class);
        return model;
    }

    @Override
    Class toEntity(ClassModel dto) {
        return modelMapper.map(dto, Class.class);
    }
}
