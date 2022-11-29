package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.dao.Student;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@Test
	public void testSaveStudent() {
		Student student = new Student();
		student.setStudentEmail("xxx@gmail.com");
		student.setName("xxx");
		
		Student studentRes = studentRepository.save(student);
		assertThat(studentRes).isNotNull();		
	}
	
	
	@Test
	public void testExistById() {
		Student student = new Student();
		student.setStudentEmail("xxx@gmail.com");
		student.setName("xxx");
		
		studentRepository.save(student);
		boolean isExist = studentRepository.existsById("xxx@gmail.com");
		assertThat(isExist).isTrue();		
	}
	
	
	@Test
	public void testFindById() {
		Student student = new Student();
		student.setStudentEmail("xxx@gmail.com");
		student.setName("xxx");
		
		studentRepository.save(student);
		Optional<Student> studentRes = studentRepository.findById("xxx@gmail.com");
		assertThat(studentRes).isNotNull();		
	}
	
	
	@Test
	public void testFindByStudentEmailIn() {
		Student student1 = new Student();
		student1.setStudentEmail("xxx@gmail.com");
		student1.setName("xxx");
		
		Student student2 = new Student();
		student2.setStudentEmail("yyy@gmail.com");
		student2.setName("yyy");
		
		Student student3 = new Student();
		student3.setStudentEmail("zzz@gmail.com");
		student3.setName("zzz");
		List<Student> students = Arrays.asList(student1, student2, student3);
		
		studentRepository.saveAll(students);
		
		List<String> studentEmails = Arrays.asList("xxx@gmail.com", "yyy@gmail.com");
		
		Set<Student> studentRes = studentRepository.findByStudentEmailIn(studentEmails);
		assertThat(studentRes).isNotNull();
		assertEquals(studentEmails.size(), studentRes.size());
	}

}
