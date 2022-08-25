package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "news")
public class News extends NewsAndNotify{

	private static final long serialVersionUID = -4407989052998829719L;

	@ManyToMany
	@JoinTable(name = "faculty_news",
				joinColumns = @JoinColumn(name = "news_id"),
				inverseJoinColumns = @JoinColumn(name = "faculty_id"))
	private Set<Faculty> faculties = new HashSet<>();

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	
}
