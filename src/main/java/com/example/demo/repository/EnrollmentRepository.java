package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Enrollment;
import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;
import com.example.demo.dao.compositeKey.EnrollmentKey;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey> {
	
	Enrollment findByTeacherAndStudentAndStatusEquals(Teacher teacher, Student student, String status);
	
	@Query("select e.student.studentEmail from Enrollment e where e.teacher.teacherEmail = :email and status = 'Registered'") 
	List<String> findStudentsForTeacher(@Param("email") String email);
	 
	 
	@Query("select e.student.studentEmail from Enrollment e where e.teacher.teacherEmail in (:emails) and status = 'Registered'") 
	List<String> findStudentsForTeachers(@Param("emails") List<String> emails);
	 
	
	@Query("select student.studentEmail from Enrollment e where "
	 		+ "status='Registered' and "
	 		+ "teacher.teacherEmail in (:emails) and "
	 		+ "student.studentEmail in (:studentEmails) "
	 		+ "group by student.studentEmail "
	 		+ "having count(teacher.teacherEmail) = :count")
	List<String> findCommonStudents(@Param("emails") List<String> emails, 
			 						@Param("studentEmails") List<String> studentEmails, 
			 						@Param("count") long count);
	 
	
	/*
	 * @Query("select e.student from Enrollment e where e.teacher.teacherEmail = :email"
	 * ) List<Student> findStudentsForTeachers(@Param("email") String email);
	 */
	
	/*
	 * @Query("select new com.example.demo.dto.TeacherResponse (:email, e.student.studentEmail) from Enrollment e where e.teacher.teacherEmail = :email"
	 * ) List<TeacherResponse> findStudentsForTeacher(@Param("email") String email);
	 */
	
}
