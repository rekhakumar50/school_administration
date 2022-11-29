package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Teacher;
import com.example.demo.dto.Response;
import com.example.demo.dto.TeacherResponse;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {
	private static final Logger LOG = LoggerFactory.getLogger(TeacherService.class);
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	
	/**
	 * This method will add a teacher to the DB
	 * @param teacher
	 */
	public void addTeachers(final Teacher teacher) {
		try {
			teacherRepository.save(teacher);
		} catch(Exception e) {
			LOG.error("Error while adding Teacher data to DB: {}", e.getMessage());
		}
	}

	
	/**
	 * This method get list of teacher with their registered students
	 */
	@Cacheable(value = "allTeacherCache")
	public Response getAllTeachers() {
		Response response = new Response();
		List<TeacherResponse> teacherResponseList = new ArrayList<>();
		
		List<String> teachers = teacherRepository.findAllTeacherEmail();
		if(CollectionUtils.isNotEmpty(teachers)) {
			LOG.debug("TeacherService.class getAllTeachers() retrieved teacher details from DB, size: {}", teachers.size());
			teachers.forEach(email -> {
				TeacherResponse teacherResponse = new TeacherResponse();
				List<String> students = enrollmentRepository.findStudentsForTeacher(email);
				teacherResponse.setEmail(email);
				teacherResponse.setStudents(students);
				teacherResponseList.add(teacherResponse);
			});
		}
		LOG.debug("TeacherService.class getAllTeachers() teacherResponseList size: {}", teacherResponseList.size());
		response.setTeachers(teacherResponseList);
		return response;
	}
	
	
	/**
	 * This method get common students for the all teachers given in the list
	 * @param teacheremails
	 * @return
	 */
	@Cacheable(value = "teacherStudentCache", key = "#teacheremails")
	public Response getcommonstudentsForTeachers(List<String> teacheremails) {
		Response response = new Response();

		List<String> studentEmails = enrollmentRepository.findStudentsForTeachers(teacheremails);
		List<String> students = enrollmentRepository.findCommonStudents(teacheremails, studentEmails, teacheremails.size());
		
		LOG.debug("TeacherService.class getcommonstudentsForTeachers() students size: {}", students.size());
		response.setStudents(students);
		return response;
	}
	
	
	/**
	 * This method checks a particular teacher exist in DB by their email
	 * @param teacherEmail
	 * @return
	 */
	public boolean existsById(String teacherEmail) {
		LOG.debug("TeacherService.class findById() stduentEmail: {}", teacherEmail);
		return teacherRepository.existsById(teacherEmail);
	}
	
	
	public boolean existsByEmails(List<String> teacherEmail) {
		List<String> teachers = teacherRepository.findAllTeacherEmail();
		return teachers.containsAll(teacherEmail);
	}
		
	
}
