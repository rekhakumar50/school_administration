package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.Student;
import com.example.demo.exception.InternalSeverException;
import com.example.demo.repository.StudentRepository;


public class StudentServiceTest {
	
	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;
	

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddStudents() throws Exception {
		Student student = new Student();
		student.setStudentEmail("studenthon@gmail.com");
		student.setName("hon");
		
		studentService.addStudents(student);
	}
	
	@Test
	public void testAddStudentsWithException() throws Exception {
		Student student = new Student();
		student.setStudentEmail("studenthon@gmail.com");
		student.setName("hon");
		when(studentRepository.save(any())).thenThrow(InternalSeverException.class);

		studentService.addStudents(student);
	}
	
	
	@Test
	public void testExistsById() {
		when(studentRepository.existsById(anyString())).thenReturn(true);

		boolean isDataExist = studentService.existsById("studenthon@gmail.com");
		assertThat(isDataExist).isTrue();	
	}

}
