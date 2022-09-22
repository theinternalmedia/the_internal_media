package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tim.converter.FacultyConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyPageRequestDto;
import com.tim.dto.faculty.FacultyRequestDto;
import com.tim.dto.faculty.FacultyUpdateRequestDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.entity.Faculty;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.FacultyRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.FacultyService;
import com.tim.utils.ValidationUtils;

@Service
public class FacultyServiceImpl implements FacultyService {
	private static final String FACULTY = "Khoa";
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private FacultyConverter facultyConverter;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	@Transactional
	public FacultyDto create(FacultyRequestDto dto) {
		// Validate input
		ValidationUtils.validateObject(dto);
		Faculty entity = facultyConverter.toEntity(dto);
		if (StringUtils.isNotBlank(dto.getHeadOfFacultyUserId())) {
			Teacher teacher = teacherRepository.getByUserId(dto.getHeadOfFacultyUserId()).orElseThrow(
					() -> new TimNotFoundException("Giảng Viên", "Mã GV", dto.getHeadOfFacultyUserId()));
			entity.setHeadOfFaculty(teacher);
		}
		return facultyConverter.toDto(facultyRepository.save(entity));
	}

	@Override
	public long toggleStatus(Set<Long> ids) {
		List<Faculty> faculties = new ArrayList<>();
		Faculty faculty;
		for (Long id : ids) {
			faculty = facultyRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(FACULTY, "ID", id.toString()));
			faculty.setStatus(!faculty.getStatus());
			faculties.add(faculty);
		}
		facultyRepository.saveAll(faculties);
		return ids.size();
	}

	
	@Override
	public FacultyDto getByCode(String code) {
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(code).orElseThrow(
				() -> new TimNotFoundException(FACULTY, "Mã khoa", code));
		return facultyConverter.toDto(faculty);
	}

	
	@Override
	@Transactional
	public FacultyDto update(FacultyUpdateRequestDto updateDto) {
		ValidationUtils.validateObject(updateDto);
		
		Faculty faculty = facultyRepository.findById(updateDto.getId()).orElseThrow(
				() -> new TimNotFoundException(FACULTY, "ID", String.valueOf(updateDto.getId())));
		
		if(!faculty.getCode().equals(updateDto.getCode())) {
			if(facultyRepository.existsByCode(updateDto.getCode())) {
				throw new TimException(ETimMessages.ALREADY_EXISTS, "Mã khoa", updateDto.getCode());
			}
		}
		faculty = facultyConverter.toEntity(updateDto, faculty);
		
		if(StringUtils.isNotBlank(updateDto.getHeadOfFacultyUserId())) {
			Teacher headOfFaculty = teacherRepository.getByUserId(updateDto.getHeadOfFacultyUserId()).orElseThrow(
					() -> new TimNotFoundException("Trưởng khoa", "Mã GV", updateDto.getHeadOfFacultyUserId()));
			faculty.setHeadOfFaculty(headOfFaculty);
		}
		return facultyConverter.toDto(facultyRepository.save(faculty));
	}

	@Override
	public List<FacultyDto> getAll(boolean status) {
		List<Faculty> faculties = facultyRepository.findAllByStatus(status);
		List<FacultyDto> dtos = facultyConverter.toDtoList(faculties);
		return dtos;
	}

	@Override
	public PagingResponseDto getPage(FacultyPageRequestDto pageRequestDto) {
		ValidationUtils.validateObject(pageRequestDto);
		
		TimSpecification<Faculty> timSpecification = new TimSpecification<Faculty>();
		timSpecification.add(new SearchCriteria("status", pageRequestDto.getStatus(), 
												SearchOperation.EQUAL));
		
		if(StringUtils.isNotEmpty(pageRequestDto.getCode())) {
			timSpecification.add(new SearchCriteria("code", pageRequestDto.getCode(), 
												SearchOperation.LIKE));
		}
		if(StringUtils.isNotEmpty(pageRequestDto.getName())) {
			timSpecification.add(new SearchCriteria("name", pageRequestDto.getName(), 
												SearchOperation.LIKE));
		}
		
		Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, 
											pageRequestDto.getSize(), Sort.by("createdDate"));
		Page<Faculty> facultyPage = facultyRepository.findAll(timSpecification, pageable);
		
		List<FacultyDto> data = facultyConverter.toDtoList(facultyPage.getContent());
		return new PagingResponseDto(facultyPage.getTotalElements(),
								facultyPage.getTotalPages(),
								facultyPage.getNumber() + 1,
								facultyPage.getSize(),
								data);
	}
}
