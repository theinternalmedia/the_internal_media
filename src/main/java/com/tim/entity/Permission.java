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

import com.tim.data.ETimPermission;

@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity{
	/**
	 * minhtuanitk43
	 */
	private static final long serialVersionUID = -7368618288132054132L;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	@Size(max=50)
	private ETimPermission code;
	
	@Column(unique = true)
	@Size(max=50)
	private String name;
	
	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<>();

	public ETimPermission getCode() {
		return code;
	}

	public void setCode(ETimPermission code) {
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
