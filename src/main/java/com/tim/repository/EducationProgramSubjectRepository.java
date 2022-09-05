package com.tim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.EducationProgramSubject;


public interface EducationProgramSubjectRepository extends JpaRepository<EducationProgramSubject, Long>{
	
	Optional<EducationProgramSubject> findBySubject_CodeAndEducationProgram_Code(String subjectCode, String eduProgramCode);
	
	List<EducationProgramSubject> findByEducationProgram_Id(Long id);
	
	List<EducationProgramSubject> findBySubject_Code(String code);
	
	boolean existsBySemester(String semester);
	
	Optional<EducationProgramSubject> getOneById(Long id);
}
