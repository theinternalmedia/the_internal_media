package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.tim.data.ETimPermissions;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

	private static final long serialVersionUID = -7368618288132054132L;

	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private ETimPermissions code;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String name;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<>();

	public ETimPermissions getCode() {
		return code;
	}

	public void setCode(ETimPermissions code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
