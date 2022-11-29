package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
	private static final long serialVersionUID = 809723884445633680L;
	
	private ResponseInformation information;	
	private List<TeacherResponse> teachers;
	private List<String> students;
	
	public ResponseInformation getInformation() {
		return information;
	}
	public void setInformation(ResponseInformation information) {
		this.information = information;
	}
	public List<TeacherResponse> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<TeacherResponse> teachers) {
		this.teachers = teachers;
	}
	public List<String> getStudents() {
		return students;
	}
	public void setStudents(List<String> students) {
		this.students = students;
	}

}
