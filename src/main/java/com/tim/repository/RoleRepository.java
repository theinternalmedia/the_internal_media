package com.tim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Role;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByNameAndStatusTrue(String string);

	Role findByName(String name);

	List<Role> findByStatusTrue();

	long countByStatus(boolean status);

	List<Role> findAllByStatus(boolean status, Pageable pageable);
}
