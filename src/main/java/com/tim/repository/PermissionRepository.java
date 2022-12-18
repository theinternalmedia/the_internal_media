package com.tim.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Permission;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Optional<Permission> findByCode(String code);
	
	List<Permission> findByCodeIn(Set<String> codeSet);

}
