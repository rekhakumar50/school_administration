package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;
import com.example.demo.dto.Request;
import com.example.demo.dto.Response;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.transformer.ResponseTransformer;
import com.example.demo.validation.RequestValidation;

public class SchoolAdministratorControllerTest {
	
	@InjectMocks
	private SchoolAdministratorController schoolAdministratorController;
	
	@Mock
	private StudentService studentService;
	
	@Mock
	private TeacherService teacherService;
	
	@Mock
	private EnrollmentService enrollmentService;
	
	@Mock
	private ResponseTransformer responseTransformer;
	
	@Mock
	private RequestValidation requestValidation;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void testAddStudents() {         
        when(requestValidation.validateStudent(any())).thenReturn(true);
        when(studentService.existsById(anyString())).thenReturn(false);
 
        Student student = new Student();
		student.setStudentEmail("studenthon@gmail.com");
		student.setName("hon");
		
		ResponseEntity<Response> responseEntity = schoolAdministratorController.addStudents(student); 
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}
	
	
	@Test
	public void testAddTeachers() {
		when(requestValidation.validateTeacher(any())).thenReturn(true);
        when(teacherService.existsById(anyString())).thenReturn(false);
		
        Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherken@gmail.com");
		teacher.setName("ken");
		
		ResponseEntity<Response> responseEntity = schoolAdministratorController.addTeachers(teacher);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}
	
	
	
	@Test 
	public void testAddTeachersStudents() {
		when(requestValidation.validateRequestWithEmailList(any())).thenReturn(true);
		when(enrollmentService.registerStudentsToTeacher(any())).thenReturn(true);
	  
		ResponseEntity<Response> responseEntity = schoolAdministratorController.addTeachersStudents(new Request());
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
	}
	  
	  
	  
	@Test	
	public void testRemoveTeachersStudents() {
		when(requestValidation.validateRequest(any())).thenReturn(true);
		when(enrollmentService.unregisterStudentFromTeacher(any())).thenReturn(true);
	  
		ResponseEntity<Response> responseEntity = schoolAdministratorController.removeTeachersStudents(new Request());
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	
	@Test
	public void testGetcommonstudentsForTeachers() {
		when(requestValidation.validateEmailList(any())).thenReturn(true);
		when(teacherService.getcommonstudentsForTeachers(any())).thenReturn(new Response());

		ResponseEntity<Response> responseEntity = schoolAdministratorController.getcommonstudentsForTeachers(Arrays.asList("studentken@gmail.com"));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	
	@Test
	public void testGetAllTeachers() {
		when(teacherService.getAllTeachers()).thenReturn(new Response());
		ResponseEntity<Response> responseEntity = schoolAdministratorController.getAllTeachers();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

}
