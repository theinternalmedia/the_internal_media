package com.tim.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.SchoolYear;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long>{

	Set<SchoolYear> findByCodeIn(Set<String> schoolYearCodes);

	Optional<SchoolYear> getByCode(String schoolYearCode);

}
