package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
	private static final long serialVersionUID = 133358535754943630L;
	
	private String teacher;
	private String student;
	private List<String> students;
	private String reason;
	
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public List<String> getStudents() {
		return students;
	}
	public void setStudents(List<String> students) {
		this.students = students;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
