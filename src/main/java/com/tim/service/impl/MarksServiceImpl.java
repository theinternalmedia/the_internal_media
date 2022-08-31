package com.tim.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.MarksConverter;
import com.tim.data.ETimMessages;
import com.tim.dto.marks.MarksDto;
import com.tim.dto.marks.MarksRequestDto;
import com.tim.entity.Marks;
import com.tim.entity.Student;
import com.tim.entity.Subject;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.MarksRepository;
import com.tim.repository.StudentRepository;
import com.tim.repository.SubjectRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.MarksService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.MessageUtils;
import com.tim.utils.ValidationUtils;

@Service
public class MarksServiceImpl implements MarksService {
	
	@Autowired
	private MarksRepository marksRepository;
	
	@Autowired
	private MarksConverter marksConverter;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private ExcelService excelService;
	
	@Override
	public MarksDto create(MarksRequestDto requestDto) {
		// Validate input 
		ValidationUtils.validateObject(requestDto);
		
		// Convert dto to entity
		Marks marks =  marksConverter.toEntity(requestDto);
		
		// Mapping student
		Student student = studentRepository.findByUserId(requestDto.getStudentUserId())
				.orElseThrow(() -> new TimNotFoundException(
						"Sinh Viên", "Mã SV", requestDto.getStudentUserId()));
		marks.setStudent(student);
		
		// Mapping teacher
		Teacher teacher = teacherRepository.findByUserId(requestDto.getTeacherUserId())
				.orElseThrow(() -> new TimNotFoundException(
						"Giảng Viên", "Mã GV", requestDto.getTeacherUserId()));
		marks.setTeacher(teacher);
		
		// Mapping subject
		Subject subject = subjectRepository.findByCode(requestDto.getSubjectCode())
				.orElseThrow(() -> new TimNotFoundException(
						"Môn Học", "Mã Môn Học", requestDto.getSubjectCode()));
		marks.setSubject(subject);
		
		// Set exam times
		long count = marksRepository.countByStudent_UserIdAndSubject_Code(
				requestDto.getStudentUserId(), requestDto.getStudentUserId());
		marks.setTimes((int) (count + 1));
		
		// Set pass
		if(marks.getFinalMarks() >= subject.getPassMarks()) {
			marks.setPass(true);
		}
		marks = marksRepository.save(marks);
		return marksConverter.toDto(marks);
	}

	@Override
	public long create(MultipartFile file) {
		List<MarksRequestDto> requestDtos = excelService
				.getListObjectFromExcelFile(file, MarksRequestDto.class);
		return create(requestDtos);
	}

	private long create(List<MarksRequestDto> requestDtos) {
		List<Marks> marksList = new ArrayList<>();
		Marks marks;
		Subject subject;
		Teacher teacher;
		Student student;
		List<String> errDetails = new ArrayList<String>();
		Set<String> subjectCodes = new HashSet<String>();
		Set<String> studentCodes = new HashSet<String>();
		Set<String> teacherCodes = new HashSet<String>();
		
		boolean check;
		for (MarksRequestDto dto : requestDtos) {
			check = true;
			// Get entity
			subject = subjectRepository.findByCode(dto.getSubjectCode()).orElse(null);
			if (subject == null) {
				subjectCodes.add(dto.getSubjectCode());
				check = false;
			} 
			teacher = teacherRepository.findByUserId(dto.getTeacherUserId()).orElse(null);
			if (teacher == null) {
				teacherCodes.add(dto.getTeacherUserId());
				check = false;
			}
			student = studentRepository.findByUserId(dto.getStudentUserId()).orElse(null);
			if (student == null) {
				studentCodes.add(dto.getStudentUserId());
				check = false;
			}
			
			// execute if check true
			if (check) {
				marks = marksConverter.toEntity(dto);
				// Mapping relationship
				marks.setSubject(subject);
				marks.setTeacher(teacher);
				marks.setStudent(student);
				// Set pass
				if(marks.getFinalMarks() >= subject.getPassMarks()) {
					marks.setPass(true);
				}
				// Set exam times
				long count = marksRepository.countByStudent_UserIdAndSubject_Code(
						dto.getStudentUserId(), dto.getStudentUserId());
				marks.setTimes((int) (count + 1));
				marksList.add(marks);
			}
		}
		if (subjectCodes.size() > 0) {
			errDetails.add(MessageUtils.get(ETimMessages.ENTITY_NOT_FOUND, 
					"Môn Học", "Mã Môn Học", subjectCodes.toString()));
		}
		if (teacherCodes.size() > 0) {
			errDetails.add(MessageUtils.get(ETimMessages.ENTITY_NOT_FOUND, 
					"Giảng Viên", "Mã GV", teacherCodes.toString()));
		}
		if (studentCodes.size() > 0) {
			errDetails.add(MessageUtils.get(ETimMessages.ENTITY_NOT_FOUND, 
					"Sinh Viên", "Mã SV", studentCodes.toString()));
		}
		if (errDetails.size() > 0) {
			throw new TimException(errDetails, ETimMessages.INVALID_OBJECT_VALUE);
		}
		marksRepository.saveAll(marksList);
		return marksList.size();
	}


}
