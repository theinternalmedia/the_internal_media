package com.tim.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.TeacherConverter;
import com.tim.data.ETimMessages;
import com.tim.data.SearchOperation;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.teacher.TeacherDto;
import com.tim.entity.Teacher;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.Utility;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	@Autowired
	private ExcelService excelService;

	@Override
	public ResponseDto save(TeacherDto dto) {
		Teacher entity = teacherConverter.toEntity(dto);
        return new ResponseDto(teacherConverter.toDto(teacherRepository.save(entity)));
	}

	@Override
	public ResponseDto findByUserId(String userId) {
		Teacher entity = teacherRepository.findByUserId(userId).orElse(null);
		if(entity == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, 
					TimConstants.ActualEntityName.TEACHER, "Mã GV", userId));
		}
		return new ResponseDto(teacherConverter.toDto(entity));
	}

	@Override
	public List<TeacherDto> importExcelFile(MultipartFile file) {
		List<TeacherDto> teachers = excelService.getListObjectFromExcelFile(file, TeacherDto.class);
		return teachers;
	}

	@Override
	public void exportExcelFile(String fileName, List<TeacherDto> teacherDtos) {
		excelService.writeListObjectToExcel(fileName, teacherDtos);
	}

	@Override
	public PagingResponseDto getPaging(String facultyCode, String name, String userId, int page, int size) {
		TimSpecification<Teacher> specification = new TimSpecification<>();
		if(StringUtils.isNotEmpty(name)) {
			specification.add(new SearchCriteria("name", facultyCode, SearchOperation.LIKE));
		}
		if(StringUtils.isNotEmpty(userId)) {
			specification.add(new SearchCriteria("userId", facultyCode, SearchOperation.EQUAL));
		}
		if(StringUtils.isNotEmpty(facultyCode)) {
			Specification<Teacher> specificationJoin = Specification.where(
					(root, query, builder) -> {
						return builder.equal(root.join("faculty").get("code"), facultyCode);
						});
			specification.and(specificationJoin);
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
		Page<Teacher> pageTeachers = teacherRepository.findAll(specification, pageable);
		return new PagingResponseDto(
				pageTeachers.getTotalElements(), 
				pageTeachers.getTotalPages(), 
				pageTeachers.getNumber(), 
				pageTeachers.getSize(), pageTeachers.getContent());
	}

}
