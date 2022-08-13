package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News extends NewsAndNotify{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -4407989052998829719L;

	@ManyToMany
	@JoinTable(name = "faculty_news",
				joinColumns = @JoinColumn(name = "news_id"),
				inverseJoinColumns = @JoinColumn(name = "faculty_id"))
	private Set<Faculty> faculties = new HashSet<>();
}
