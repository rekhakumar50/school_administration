package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
		
	Set<Student> findByStudentEmailIn(List<String> studentEmail);
	
}
