package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.EducationProgram;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, Long>, 
		JpaSpecificationExecutor<EducationProgram>{
	boolean existsByCode(String code);
	
	Optional<EducationProgram> getByCode(String code);

	Optional<EducationProgram> findByCodeAndStatusTrue(String code);

	boolean existsBySchoolYear_CodeAndFaculty_Code(String schoolYearCode, String facultyCode);
}
