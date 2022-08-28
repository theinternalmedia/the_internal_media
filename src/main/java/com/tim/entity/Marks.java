package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "marks")
public class Marks extends BaseEntity {

	private static final long serialVersionUID = -2808568424623733508L;

	@Size(min = 0, max = 10)
	private float finalMarks;

	@Column(columnDefinition = "integer default 1")
	@Min(value = 1)
	private int times = 1;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean pass = false;
	
	@OneToOne
	private Student student;
	
	@OneToOne
	private Subject subject;

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

	private String note;

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
}
