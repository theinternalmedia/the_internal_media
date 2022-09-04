package com.tim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.EducationProgramSubject;


public interface EducationProgramSubjectRepository extends JpaRepository<EducationProgramSubject, Long>{
	
	List<EducationProgramSubject> findBySemesterAndEducationProgram_Id(String semester, Long Id);
	
	List<EducationProgramSubject> findBySubject_Code(String code);
	
	boolean existsBySemester(String semester);
}
