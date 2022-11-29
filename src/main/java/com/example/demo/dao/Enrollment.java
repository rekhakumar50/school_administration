package com.example.demo.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.dao.compositeKey.EnrollmentKey;

@Entity
@IdClass(EnrollmentKey.class)
@Table(name = "enrollments")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 50087361938970086L;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "student_email")
    private Student student;

	@Id
    @ManyToOne
    @JoinColumn(name = "teacher_email")
    private Teacher teacher;

	@Column(nullable = false)
	private String status;
	
	private String reason;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime registeredDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDateTime registeredDate) {
		this.registeredDate = registeredDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}