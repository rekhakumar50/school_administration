package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepository;
	
	
	/**
	 * This method add student to the DB
	 * @param student
	 * @throws Exception
	 */
	public void addStudents(final Student student) throws Exception {
		try {
			studentRepository.save(student);
		} catch(Exception e) {
			LOG.error("Error while adding student data to DB: {}", e.getMessage());
		}
	}
	
	
	/**
	 * This method checks a particular student exist in DB by their email
	 * @param stduentEmail
	 * @return
	 */
	public boolean existsById(String stduentEmail) {
		LOG.debug("StudentService.class findById() stduentEmail: {}", stduentEmail);
		return studentRepository.existsById(stduentEmail);
	}

}
