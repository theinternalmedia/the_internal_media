package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Feedback extends BaseEntity {

	private static final long serialVersionUID = 8873909639595411496L;
	
	private String userId;
	@Column(nullable = false)
	private String message;
	private String name;
	@Column(length = 20)
	private String contactNumber;
	private String email;
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean seen = false;
}
