package com.example.demo.validation;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;
import com.example.demo.dto.Request;
import static com.example.demo.util.Utility.*;

@Component
public class RequestValidation {
	private static final Logger LOG = LoggerFactory.getLogger(RequestValidation.class);

	
	/**
	 * Validate Student object with valid email and name
	 * @param student
	 * @return
	 */
	public boolean validateStudent(final Student student) {
		boolean isValid = false;
		if(Objects.nonNull(student) && 
				StringUtils.isNotEmpty(student.getStudentEmail()) && 
				StringUtils.isNotEmpty(student.getName())) {
			isValid = validateEmail(student.getStudentEmail()) && validateName(student.getName());
		} 
		LOG.debug("validateStudent() isValid: {}", isValid);
		return isValid;
	}
	
	
	/**
	 * Validate Teacher object with valid email and name
	 * @param teacher
	 * @return
	 */
	public boolean validateTeacher(final Teacher teacher) {
		boolean isValid = false;
		if(Objects.nonNull(teacher)) {
			isValid = validateEmail(teacher.getTeacherEmail()) && validateName(teacher.getName());
		} 
		LOG.debug("validateTeacher() isValid: {}", isValid);
		return isValid;
	}
	
	
	/**
	 * Validate list of email
	 * @param teacherEmails
	 * @return
	 */
	public boolean validateEmailList(final List<String> teacherEmails) {
		boolean isValid = false;
		if(CollectionUtils.isNotEmpty(teacherEmails)) {
			for(String email : teacherEmails) {
				isValid = validateEmail(email);
				if(!isValid) 
					break;
			}
		}
		LOG.debug("validateEmailList() isValid: {}", isValid);
		return isValid;
	}
	
	
	/**
	 * Validate Request Object which has Student email List and Teacher email
	 * @param request
	 * @return
	 */
	public boolean validateRequestWithEmailList(final Request request) {
		boolean isValid = false;
		if(Objects.nonNull(request)) {
			isValid = validateEmail(request.getTeacher());
			if(CollectionUtils.isNotEmpty(request.getStudents()) && isValid) {
				for(String email : request.getStudents()) {
					isValid = validateEmail(email);
					if(!isValid) 
						break;
				}
			}
		}
		LOG.debug("validateRequestWithEmailList() isValid: {}", isValid);
		return isValid;
	}
	
	
	/**
	 * Validate Request Object which has Student email and Teacher email
	 * @param request
	 * @return
	 */
	public boolean validateRequest(final Request request) {
		boolean isValid = false;
		if(Objects.nonNull(request)) {
			isValid = validateEmail(request.getTeacher()) && validateEmail(request.getStudent());
		}
		LOG.debug("validateRequest() isValid: {}", isValid);
		return isValid;
	}
	

}
