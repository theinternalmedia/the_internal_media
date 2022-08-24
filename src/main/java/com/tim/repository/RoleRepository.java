package com.tim.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.data.ETimRoles;
import com.tim.entity.Role;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByNameAndStatusTrue(String string);

	Role findByName(String name);

	List<Role> findByStatusTrue();

	long countByStatus(boolean status);

	List<Role> findAllByStatus(boolean status, Pageable pageable);

	Set<Role> findByCodeIn(Collection<String> roleCodes);

	Role findByCode(ETimRoles roleTeacher);
}
