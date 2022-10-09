package com.tim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim.dto.marks.CustomMarks;
import com.tim.dto.marks.InterfaceBaseMarksDto;
import com.tim.dto.marks.CustomMarksInClass;
import com.tim.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long>, JpaSpecificationExecutor<Marks> {

	long countByStudent_UserIdAndSubject_Code(String studentUserId, 
										String studentUserId2);

	List<Marks> findByStudent_UserId(String userId);
	
	
	@Query("SELECT new com.tim.dto.marks.CustomMarks("
			+ "mk.finalMarks, mk.pass, eps.semester, sb.name) "
		+ "FROM Marks mk "
		+ "JOIN mk.subject sb "
		+ "JOIN sb.educationProgramSubjects eps "
		+ "WHERE mk.student.userId = :studentId")
	List<CustomMarks> findCustomMarksByStudentId(
							@Param("studentId")String studentId);
	
	
	@Query(value = "Select s.name, s.user_id userId, s.dob, mk.final_marks finalMarks, mk2.MaxTimes times " + 
			"from marks mk " + 
			"INNER JOIN " + 
			"(select MAX(times) As MaxTimes, student_id " + 
			"from marks " + 
			"group by student_id) mk2 " + 
			"on mk.student_id = mk2.student_id " + 
			"and mk.times = mk2.MaxTimes " + 
			"INNER JOIN " + 
			"student s " + 
			"on s.id = mk.student_id " + 
			"INNER JOIN " + 
			"subject sb " + 
			"on sb.id = mk.subject_id " + 
			"INNER JOIN " + 
			"class cl " + 
			"on s.class_id = cl.id " + 
			"WHERE " + 
			"sb.code = :subjectCode and cl.code = :classCode " +
			"order by s.name asc",
			nativeQuery = true)
	List<InterfaceBaseMarksDto> findCustomMarksBySubjectAndClass(
							@Param("subjectCode") String subjectCode,
							@Param("classCode") String classCode);
}
