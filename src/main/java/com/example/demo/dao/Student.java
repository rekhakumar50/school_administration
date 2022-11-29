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
@Table(name = "students")
public class Student implements Serializable {
	private static final long serialVersionUID = -5379531578181408012L;
	
	@Id
	@JsonProperty("email")
	private String studentEmail;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "student")
	private Set<Enrollment> enrollments;

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
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
