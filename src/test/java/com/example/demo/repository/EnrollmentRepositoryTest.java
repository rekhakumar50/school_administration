package com.example.demo.repository;

import static com.example.demo.constant.Constants.REGISTERED;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.dao.Enrollment;
import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EnrollmentRepositoryTest {
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	
	@Test
	public void testSaveEnrollment() {
		Student student = new Student();
		student.setStudentEmail("studenthon@gmail.com");
		student.setName("hon");
		
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherjoe@gmail.com");
		teacher.setName("joe");
		
		Enrollment enrollment = new Enrollment();
		enrollment.setTeacher(teacher);
		enrollment.setStudent(student);
		enrollment.setStatus(REGISTERED);
		
		Enrollment enrollmentRes = enrollmentRepository.save(enrollment);
		assertThat(enrollmentRes).isNotNull();		
	}
	
	
	@Test
	public void testSaveAllEnrollment() {
		Student student1 = new Student();
		student1.setStudentEmail("studenthon@gmail.com");
		student1.setName("hon");
		
		Student student2 = new Student();
		student2.setStudentEmail("studentjon@gmail.com");
		student2.setName("jon");
		
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherjoe@gmail.com");
		teacher.setName("joe");
		
		Enrollment enrollment1 = new Enrollment();
		enrollment1.setTeacher(teacher);
		enrollment1.setStudent(student1);
		enrollment1.setStatus(REGISTERED);
		
		Enrollment enrollment2 = new Enrollment();
		enrollment2.setTeacher(teacher);
		enrollment2.setStudent(student2);
		enrollment2.setStatus(REGISTERED);
		List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);

		List<Enrollment> enrollmentsList = enrollmentRepository.saveAll(enrollments);
		assertThat(enrollmentsList).isNotNull();
	}
	
	
	
	@Test
	public void testFindStudentsForTeacher() {
		List<String> students = enrollmentRepository.findStudentsForTeacher("teacherjoe@gmail.com");
		assertThat(students).isNotNull();
	}
	
	
	@Test
	public void testFindStudentsForTeachers() {
		List<String> students = enrollmentRepository.findStudentsForTeachers(Arrays.asList("teacherjoe@gmail.com", "teacherken@gmail.com"));
		assertThat(students).isNotNull();
	}
	
	
	
	@Test
	public void testFindCommonStudents() {
		List<String> students = Arrays.asList("studenthon@gmail.com", "studenthon@gmail.com","studentjon@gmail.com");
		List<String> teachers = Arrays.asList("teacherjoe@gmail.com", "teacherken@gmail.com");

		List<String> studentsRes = enrollmentRepository.findCommonStudents(teachers, students, students.size());
		assertThat(studentsRes).isNotNull();
	}

}
