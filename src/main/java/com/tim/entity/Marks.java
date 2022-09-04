package com.tim.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "marks")
public class Marks extends BaseEntity {

	private static final long serialVersionUID = -2808568424623733508L;

	@Min(value = 0)
	@Max (value = 10)
	private float finalMarks;

	@Column(columnDefinition = "integer default 1")
	@Min(value = 1)
	private int times = 1;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean pass = false;
	
	@Column
	private String note;
	
	@Column
	private LocalDate date;
	
	@Column
	private String time;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private Subject subject;
	
	@ManyToOne
	private Teacher teacher;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public float getFinalMarks() {
		return finalMarks;
	}

	public void setFinalMarks(float finalMarks) {
		this.finalMarks = finalMarks;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
