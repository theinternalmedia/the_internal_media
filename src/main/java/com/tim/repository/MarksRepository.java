package com.tim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim.dto.marks.CustomMarks;
import com.tim.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long>, JpaSpecificationExecutor<Marks> {

	long countByStudent_UserIdAndSubject_Code(String studentUserId, String studentUserId2);

	List<Marks> findByStudent_UserId(String userId);
	
	
	@Query("SELECT new com.tim.dto.marks.CustomMarks("
			+ "mk.finalMarks, mk.pass, eps.semester, sb.name) "
		+ "FROM Marks mk "
		+ "JOIN mk.subject sb "
		+ "JOIN sb.educationProgramSubjects eps "
		+ "WHERE mk.student.userId = :studentId")
	List<CustomMarks> findCustomMarksByStudentId(@Param("studentId")String studentId);
}
