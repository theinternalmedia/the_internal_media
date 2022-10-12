package com.tim.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim.dto.marks.CustomMarks;
import com.tim.dto.marks.InterfaceBaseMarksDto;
import com.tim.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long>, JpaSpecificationExecutor<Marks> {

	long countByStudent_UserIdAndSubject_Code(String studentUserId, 
										String studentUserId2);

	List<Marks> findByStudent_UserId(String userId);
	
	Page<Marks> findBySubject_CodeAndStudent_Classz_CodeOrderByStudent_NameAsc(
			String subjectCode, String classCode, Pageable pageable);
	
	
	@Query("SELECT new com.tim.dto.marks.CustomMarks("
			+ "mk.finalMarks, mk.pass, eps.semester, sb.name) "
		+ "FROM Marks mk "
		+ "JOIN mk.subject sb "
		+ "JOIN sb.educationProgramSubjects eps "
		+ "WHERE mk.student.userId = :studentId")
	List<CustomMarks> findCustomMarksByStudentId(
							@Param("studentId")String studentId);
	
	
	@Query(value = 
			"SELECT s.name, s.user_id userId, s.dob, mk.final_marks finalMarks, mk2.MaxTimes times " + 
			"FROM marks mk " + 
			"INNER JOIN " + 
				"(select MAX(times) As MaxTimes, student_id " + 
				"FROM marks " + 
				"GROUP BY student_id) mk2 " + 
				"ON mk.student_id = mk2.student_id " + 
				"AND mk.times = mk2.MaxTimes " + 
			"INNER JOIN student s " + 
				"ON s.id = mk.student_id " + 
			"INNER JOIN subject sb " + 
				"ON sb.id = mk.subject_id " + 
			"INNER JOIN class cl " + 
				"ON s.class_id = cl.id " + 
			"WHERE " + 
				"sb.code = :subjectCode AND cl.code = :classCode " +
			"ORDER BY s.name asc",
			nativeQuery = true)
	List<InterfaceBaseMarksDto> findCustomMarksBySubjectAndClass(
							@Param("subjectCode") String subjectCode,
							@Param("classCode") String classCode);
}
