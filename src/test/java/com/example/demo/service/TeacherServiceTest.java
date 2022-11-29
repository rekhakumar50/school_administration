package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.Teacher;
import com.example.demo.dto.Response;
import com.example.demo.exception.InternalSeverException;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.TeacherRepository;

public class TeacherServiceTest {

	
	@InjectMocks
	private TeacherService teacherService;
	
	@Mock
	private TeacherRepository teacherRepository;
	
	@Mock
	private EnrollmentRepository enrollmentRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testAddTeacher() throws Exception {
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherken@gmail.com");
		teacher.setName("ken");
		
		teacherService.addTeachers(teacher);
	}
	
	@Test
	public void testAddTeacherWithException() throws Exception {
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherken@gmail.com");
		teacher.setName("ken");
		when(teacherRepository.save(any())).thenThrow(InternalSeverException.class);

		teacherService.addTeachers(teacher);
	}

	
	@Test
	public void testGetAllTeachers() {
		when(teacherRepository.findAllTeacherEmail()).thenReturn(Arrays.asList("teacherjoe@gmail.com", "teacherken@gmail.com"));
		when(enrollmentRepository.findStudentsForTeacher(anyString())).thenReturn(Arrays.asList("studenthon@gmail.com"));

		Response response = teacherService.getAllTeachers();
		assertThat(response).isNotNull();
		assertThat(response.getTeachers()).isNotNull();
	}
	
	
	@Test
	public Response testGetcommonstudentsForTeachers(List<String> teacheremails) {
		when(enrollmentRepository.findCommonStudents(any(), any(), any())).thenReturn(Arrays.asList("studenthon@gmail.com"));
		when(enrollmentRepository.findStudentsForTeachers(any())).thenReturn(Arrays.asList("studenthon@gmail.com"));

		Response response = teacherService.getcommonstudentsForTeachers(Arrays.asList("teacherjoe@gmail.com"));
		assertThat(response).isNotNull();
		assertThat(response.getStudents()).isNotNull();
		return response;
	}
	
	
	@Test
	public void testExistsById() {
		when(teacherRepository.existsById(anyString())).thenReturn(true);

		boolean isDataExist = teacherService.existsById("studenthon@gmail.com");
		assertThat(isDataExist).isTrue();	
	}
	
}
