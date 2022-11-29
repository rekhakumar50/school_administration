package com.example.demo.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "teachers")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 7278380071135624622L;
	
	@Id
	@JsonProperty("email")
	private String teacherEmail;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "teacher")
	private Set<Enrollment> enrollments;

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
}
