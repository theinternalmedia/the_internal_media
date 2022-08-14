package com.tim.service.impl;

import com.tim.converter.StudentConverter;
import com.tim.dto.ResponseDto;
import com.tim.dto.student.StudentDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.Student;
import com.tim.entity.Teacher;
import com.tim.repository.ClassRepository;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import com.tim.service.excel.ExcelService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConverter studentConverter;
    @Autowired
	private ExcelService excelService;
    @Autowired
    private ClassRepository classRepository;

    @Transactional
    @Override
    public ResponseDto save(StudentDto studentDto) {
        Student entity = studentConverter.toEntity(studentDto);
        return new ResponseDto(studentRepository.save(entity));
    }

    @Transactional
	@Override
	public ResponseDto save(MultipartFile file) {
		List<StudentDto> dtoList = excelService.getListObjectFromExcelFile(file, StudentDto.class);
		List<Student> entityList = new ArrayList<>();
		dtoList.forEach(item -> {
			Student entity = studentConverter.toEntity(item);
			Classz classz = classRepository.getByCode(item.getClassCode());
			entity.setClassz(classz);
			entityList.add(entity);
		});
		studentRepository.saveAll(entityList);
		return new ResponseDto();
	}
}
