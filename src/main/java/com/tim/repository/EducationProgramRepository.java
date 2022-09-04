package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.EducationProgram;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, Long>{
	boolean existsByCode(String code);
	
	Optional<EducationProgram> getByCode(String code);
}
