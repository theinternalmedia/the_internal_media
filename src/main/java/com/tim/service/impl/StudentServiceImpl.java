package com.tim.service.impl;

import com.tim.converter.StudentConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentDto;
import com.tim.entity.Student;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import com.tim.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentConverter studentConverter;


    @Override
    public ResponseDto create(StudentDto dto) {
        Student student = studentConverter.toEntity(dto);
        return new ResponseDto(studentConverter.toDto(studentRepository.save(student)));
    }

    @Override
    public ResponseDto update(StudentDto dto) {
        Student student = studentConverter.toEntity(dto);
        Long studentId = dto.getId();
        if(studentId != null){
            Student oldStudent = studentRepository.getOneById(studentId);
            oldStudent.setAClass(student.getAClass());
            oldStudent.setRoles(student.getRoles());
            oldStudent.setPhone(student.getPhone());
            oldStudent.setGender(student.isGender());
            oldStudent.setAddress(student.getAddress());
            return new ResponseDto(studentConverter.toDto(studentRepository.save(oldStudent)));
        }
        return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
    }

    @Override
    public ResponseDto getOne(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return new ResponseDto(studentConverter.toDto(student.get()));
        }
        return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
                                TimConstants.ActualEntityName.STUDENT,
                                "Id", id.toString()));
    }

    @Override
    public ResponseDto getByUserName(String name){
        Student student = studentRepository.findByUserId(name).orElse(null);
        if(student != null){
            return new ResponseDto(studentConverter.toDto(student));
        }
        return new ResponseDto(Utility.getMessage(ETimMessages.USER_NOT_FOUND));
    }

    @Override
    public ResponseDto getByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        if(student != null){
            return new ResponseDto(studentConverter.toDto(student));
        }
        return new ResponseDto(Utility.getMessage(ETimMessages.USER_NOT_FOUND));
    }

    @Override
    public ResponseDto toggleStatus(Long id) {
        Student student = studentRepository.getOneById(id);
        student.setStatus(false);
        studentRepository.save(student);
        return new ResponseDto();
    }
}
