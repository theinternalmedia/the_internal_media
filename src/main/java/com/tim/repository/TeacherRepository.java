package com.tim.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim.entity.Teacher;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
	Optional<Teacher> findByUserId(String userId);

	boolean existsByUserId(String userId);

	List<Teacher> getByFaculty_CodeIn(Collection<String> facultyCodes);

	List<Teacher> getByStatusTrue();

	boolean existsByEmail(String email);

	List<Teacher> findByUserIdIn(Set<String> userIdSet);

	List<Teacher> findByEmailIn(Set<String> emailSet);

	@Query(value = "SELECT t FROM Teacher t WHERE t.userId <> :userId AND t.status = true")
	List<Teacher> findByUserIdAndStatusTrue(@Param("userId") String userId);

}
