package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.Enrollment;
import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;
import com.example.demo.dto.Request;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

public class EnrollmentServiceTest {
	
	@InjectMocks
	private EnrollmentService enrollmentService;
	
	@Mock
	private EnrollmentRepository enrollmentRepository;
	
	@Mock
	private TeacherRepository teacherRepository;
	
	@Mock
	private StudentRepository studentRepository;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testRegisterStudentsToTeacher() {
		Request request = new Request();
		request.setTeacher("teacherken@gmail.com");
		request.setStudents(Arrays.asList("studenthon@gmail.com", "studentkon@gmail.com"));
		
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherken@gmail.com");
		teacher.setName("ken");
		
		Student student1 = new Student();
		student1.setStudentEmail("studenthon@gmail.com");
		student1.setName("hon");
		
		Student student2 = new Student();
		student2.setStudentEmail("studentkon@gmail.com");
		student2.setName("ken");
		Set<Student> students = new HashSet<>();
		students.add(student1);
		students.add(student2);

		when(teacherRepository.findById(request.getTeacher())).thenReturn(Optional.of(teacher));
		when(studentRepository.findByStudentEmailIn(request.getStudents())).thenReturn(students);

		boolean isDataExist = enrollmentService.registerStudentsToTeacher(request);
		assertThat(isDataExist).isTrue();	
	}
	
	
	@Test
	public void testUnregisterStudentFromTeacher() {
		Request request = new Request();
		request.setTeacher("teacherken@gmail.com");
		request.setStudent("studenthon@gmail.com");
		
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherken@gmail.com");
		teacher.setName("ken");
		
		Student student = new Student();
		student.setStudentEmail("studenthon@gmail.com");
		student.setName("hon");
		
		Enrollment enrollment = new Enrollment();
		enrollment.setStudent(student);
		enrollment.setTeacher(teacher);
						
		when(teacherRepository.findById(request.getTeacher())).thenReturn(Optional.of(teacher));
		when(studentRepository.findById(request.getStudent())).thenReturn(Optional.of(student));
		when(enrollmentRepository.findByTeacherAndStudentAndStatusEquals(any(), any(), anyString())).thenReturn(enrollment);

		boolean isDataExist = enrollmentService.unregisterStudentFromTeacher(request);
		assertThat(isDataExist).isTrue();	
	}
	

}
