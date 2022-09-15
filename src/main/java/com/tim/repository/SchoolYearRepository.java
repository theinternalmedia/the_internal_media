package com.tim.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.SchoolYear;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long>, JpaSpecificationExecutor<SchoolYear>{

	Set<SchoolYear> findByCodeIn(Set<String> schoolYearCodes);

	Optional<SchoolYear> findByCode(String schoolYearCode);

	boolean existsByCode(String code);

	Optional<SchoolYear> findByCodeAndStatusTrue(String code);
	
	List<SchoolYear> findAllByStatus(boolean status);

}
