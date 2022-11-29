package com.example.demo.dao.compositeKey;

import java.io.Serializable;

public class EnrollmentKey implements Serializable {
	private static final long serialVersionUID = 4184360197094774536L;

	private String student;

    private String teacher;

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
}
