package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true, of = "code")
@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {

	private static final long serialVersionUID = -7368618288132054132L;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	@Code
	private String code;

	@Column(unique = true, nullable = false, length = 100)
	@Size(max = 100)
	private String name;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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
