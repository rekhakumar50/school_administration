package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class TeacherResponse implements Serializable {
	private static final long serialVersionUID = 5801267836601237677L;
	
	private String email;
	private List<String> students;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getStudents() {
		return students;
	}
	public void setStudents(List<String> students) {
		this.students = students;
	}

}
