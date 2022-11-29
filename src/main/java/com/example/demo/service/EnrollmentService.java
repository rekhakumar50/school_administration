package com.example.demo.service;

import static com.example.demo.constant.Constants.REGISTERED;
import static com.example.demo.constant.Constants.DEREGISTERED;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Enrollment;
import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;
import com.example.demo.dto.Request;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class EnrollmentService {
	private static final Logger LOG = LoggerFactory.getLogger(EnrollmentService.class);
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	/**
	 * This method will add students to a teacher
	 * @param request
	 * @return
	 */
	public boolean registerStudentsToTeacher(final Request request) {
		boolean isDataExist = false;
		Optional<Teacher> teacherOp = teacherRepository.findById(request.getTeacher());
		Set<Student> students = studentRepository.findByStudentEmailIn(request.getStudents());
		if(teacherOp.isPresent() && CollectionUtils.isNotEmpty(students)) {
			LOG.debug("EnrollmentService.class registerStudentsToTeacher() Teacher and Student data is present in DB");
			List<Enrollment> enrollmentList = students.stream()
												.filter(Objects::nonNull)
												.filter(s -> Objects.isNull(this.getStudentTeacherDetailsRegistered(s, teacherOp.get())))
												.map(s -> processStudentTeacher.apply(s, teacherOp.get()))
												.collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(enrollmentList)) {
				isDataExist = true;
				enrollmentRepository.saveAll(enrollmentList);
				LOG.debug("EnrollmentService.class registerStudentsToTeacher() added enrollment details in DB");
			}
		}
		LOG.debug("EnrollmentService.class registerStudentsToTeacher() isDataExist: {}", isDataExist);
		return isDataExist;
	}
	
	
	
	/**
	 * This method will remove a student from a teacher
	 * @param request
	 * @return
	 */
	public boolean unregisterStudentFromTeacher(final Request request) {
		boolean isDataExist = false;
		Optional<Teacher> teacherOp = teacherRepository.findById(request.getTeacher());
		Optional<Student> studentOp = studentRepository.findById(request.getStudent());
		
		if(teacherOp.isPresent() && studentOp.isPresent()) {
			LOG.debug("EnrollmentService.class unregisterStudentFromTeacher() Teacher and Student data is present in DB");
			Enrollment enrollment = this.getStudentTeacherDetailsRegistered(studentOp.get(), teacherOp.get());
			if(Objects.nonNull(enrollment)) {
				LOG.debug("EnrollmentService.class unregisterStudentFromTeacher() enrollment data is present in DB");
				isDataExist = true;
				enrollment.setStatus(DEREGISTERED);
				enrollment.setReason(request.getReason());
				enrollmentRepository.save(enrollment);
				LOG.debug("EnrollmentService.class unregisterStudentFromTeacher() updated enrollment details in DB");
			} else {
				LOG.debug("Student already de-registered for the Teacher");
			}
		}
		LOG.debug("EnrollmentService.class unregisterStudentFromTeacher() isDataExist: {}", isDataExist);
		return isDataExist;
	}
	
	
	
	BiFunction<Student, Teacher, Enrollment> processStudentTeacher = (student, teacher) -> {	
		Enrollment enrollment = null;
		Enrollment enrollmentDeregistered = this.getStudentTeacherDetailsDeregistered(student, teacher);
		if(Objects.nonNull(enrollmentDeregistered)) {
			enrollmentDeregistered.setStatus(REGISTERED);
			enrollmentDeregistered.setReason(null);
			return enrollmentDeregistered;
		} else {
			enrollment = new Enrollment();
			enrollment.setTeacher(teacher);
			enrollment.setStudent(student);
			enrollment.setStatus(REGISTERED);
		}
				
		return enrollment;
	};
	
	
	/**
	 * Get Registered students of the Teacher 
	 * @param student
	 * @param teacher
	 * @return
	 */
	private Enrollment getStudentTeacherDetailsRegistered(Student student, Teacher teacher) {
		return enrollmentRepository.findByTeacherAndStudentAndStatusEquals(teacher, student, REGISTERED);
	}
	
	
	/**
	 * Get de-registered students of the Teacher 
	 * @param student
	 * @param teacher
	 * @return
	 */
	private Enrollment getStudentTeacherDetailsDeregistered(Student student, Teacher teacher) {
		return enrollmentRepository.findByTeacherAndStudentAndStatusEquals(teacher, student, DEREGISTERED);
	}
	

}
