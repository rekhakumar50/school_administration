package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
	
	@Query("select t.teacherEmail from Teacher t")
	List<String> findAllTeacherEmail();

}
