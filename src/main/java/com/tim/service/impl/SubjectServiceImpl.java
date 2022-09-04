package com.tim.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.SubjectConverter;
import com.tim.data.ETimMessages;
import com.tim.dto.subject.SubjectDto;
import com.tim.dto.subject.SubjectRequestDto;
import com.tim.dto.subject.SubjectUpdateRequestDto;
import com.tim.entity.Subject;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.SubjectRepository;
import com.tim.service.SubjectService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.ValidationUtils;

@Service
public class SubjectServiceImpl implements SubjectService{
	private static final String SUBJECT = "Môn Học";
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private SubjectConverter subjectConverter;
	
	@Autowired
	private ExcelService excelService;

	@Override
	public SubjectDto create(SubjectRequestDto requestDto) {
		// Validate input 
		ValidationUtils.validateObject(requestDto);
		
		// Check exists code
		if (subjectRepository.existsByCode(requestDto.getCode())) {
			throw new TimException(
					ETimMessages.ALREADY_EXISTS, "Mã Môn Học", requestDto.getCode());
		}
		
		// Convert dto to entity 
		Subject subject = subjectConverter.toEntity(requestDto);
		subject = subjectRepository.save(subject);
		
		return subjectConverter.toDto(subject);
	}

	@Override
	public SubjectDto update(SubjectUpdateRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		// Get entity
		Subject subject = subjectRepository.findById(requestDto.getId()).orElseThrow(
				() -> new TimNotFoundException(SUBJECT, "ID", requestDto.getId().toString()));
		// Check exists code
		if (!subject.getCode().equals(requestDto.getCode())) {
			if (subjectRepository.existsByCode(requestDto.getCode())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Mã Môn Học", requestDto.getCode());
			}
		}
		// Convert dto to entity from requestDto and oldEntity
		subject = subjectConverter.toEntity(requestDto, subject);
		
		subject = subjectRepository.save(subject);
		
		return subjectConverter.toDto(subject);
	}

	@Override
	public long create(MultipartFile file) {
		List<SubjectRequestDto> requestDtos = excelService
				.getListObjectFromExcelFile(file, SubjectRequestDto.class);
		return create(requestDtos);
	}

	private long create(List<SubjectRequestDto> requestDtos) {
		List<Subject> subjectList = new ArrayList<>();
		Subject subject;
		Set<String> subjectCodes = new HashSet<String>();
		
		for (SubjectRequestDto dto : requestDtos) {
			// Check exists code
			if (subjectRepository.existsByCode(dto.getCode())) {
				subjectCodes.add(dto.getCode());
			}
			if (subjectCodes.isEmpty()) {
				subject = subjectConverter.toEntity(dto);
				subjectList.add(subject);
			}
		}
		if (subjectCodes.size() > 0) {
			throw new TimException(List.of(subjectCodes.toString()), 
					ETimMessages.ALREADY_EXISTS, "Mã Môn Học", "[Chi tiết]");
		}
		subjectRepository.saveAll(subjectList);
		return subjectList.size();
	}

	@Override
	public long toggleStatus(Set<Long> ids) {
		List<Subject> subjects = new ArrayList<>();
		Subject subject;
		for (Long id : ids) {
			subject = subjectRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(SUBJECT, "ID", id.toString()));
			subject.setStatus(!subject.getStatus());
			subjects.add(subject);
		}
		subjectRepository.saveAll(subjects);
		return ids.size();
	}

}
