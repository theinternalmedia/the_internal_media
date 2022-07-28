package com.tim.converter;

import com.tim.entity.Student;
import com.tim.model.StudentModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter extends AbstractConverter<StudentModel, Student> {

    /**
     * thinhnguyen
     */

    @Autowired
    private ModelMapper modelMapper;

    @Override
    StudentModel toDto(Student entity) {
        StudentModel model = modelMapper.map(entity, StudentModel.class);
        return model;
    }

    @Override
    Student toEntity(StudentModel dto) {
        return modelMapper.map(dto, Student.class);
    }
}
