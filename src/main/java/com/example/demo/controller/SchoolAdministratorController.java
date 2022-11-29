package com.example.demo.controller;

import static com.example.demo.constant.Constants.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Student;
import com.example.demo.dao.Teacher;
import com.example.demo.dto.Request;
import com.example.demo.dto.Response;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.DuplicateDataException;
import com.example.demo.exception.InternalSeverException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.transformer.ResponseTransformer;
import com.example.demo.validation.RequestValidation;

@RestController
@RequestMapping("/api")
public class SchoolAdministratorController {
	private static final Logger LOG = LoggerFactory.getLogger(SchoolAdministratorController.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private ResponseTransformer responseTransformer;
	
	@Autowired
	private RequestValidation requestValidation;
	
	
	/**
	 * add a new student
	 * @param student
	 * @return
	 */
	@PostMapping(value = "/students", consumes = "application/json")
	public ResponseEntity<Response> addStudents(@RequestBody Student student) {
		try {
			this.processValidation(requestValidation.validateStudent(student));
			boolean isDataExist = studentService.existsById(student.getStudentEmail());
			if(!isDataExist) {
				studentService.addStudents(student);
			} else {
				throw new DuplicateDataException(DATA_EXIST_ERROR);
			}
		} catch(InvalidDataException ex) {
			throw new InvalidDataException(INVALID_DATA_ERROR);
		} catch(DuplicateDataException ex) {
			throw new DuplicateDataException(DATA_EXIST_ERROR);
		} catch(Exception e) {
			throw new InternalSeverException(INTERNAL_SERVER_STUDENT_ERROR);
		}
		LOG.info("Student data saved into DB");
		Response response = responseTransformer.getResponse(HttpStatus.CREATED.value(), DATA_SAVED);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	/**
	 * add a new Teacher
	 * @param teacher
	 * @return
	 */
	@PostMapping(value = "/teachers", consumes = "application/json")
	public ResponseEntity<Response> addTeachers(@RequestBody Teacher teacher) {
		try {
			this.processValidation(requestValidation.validateTeacher(teacher));
			boolean isDataExist = teacherService.existsById(teacher.getTeacherEmail());
			if(!isDataExist) {
				teacherService.addTeachers(teacher);
			} else {
				throw new DuplicateDataException(DATA_EXIST_ERROR);
			}
		} catch(InvalidDataException ex) {
			throw new InvalidDataException(INVALID_DATA_ERROR);
		} catch(DuplicateDataException ex) {
			throw new DuplicateDataException(DATA_EXIST_ERROR);
		} catch(Exception e) {
			throw new InternalSeverException(INTERNAL_SERVER_STUDENT_ERROR);
		}
		LOG.info("Teacher data saved into DB");
		Response response = responseTransformer.getResponse(HttpStatus.CREATED.value(), DATA_SAVED);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	/**
	 * register a student to a teacher
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/register", consumes = "application/json")
	@ResponseStatus
	public ResponseEntity<Response> addTeachersStudents(@RequestBody Request request) {
		try {
			this.processValidation(requestValidation.validateRequestWithEmailList(request));
			boolean isDataExist = enrollmentService.registerStudentsToTeacher(request);
			if(!isDataExist) {
				throw new DataNotFoundException(DATA_NOT_FOUND_REGISTER_ERROR);
			}
		} catch(InvalidDataException ex) {
			throw new InvalidDataException(INVALID_DATA_ERROR);
		} catch(DataNotFoundException ex) {
			throw new DataNotFoundException(DATA_NOT_FOUND_REGISTER_ERROR);
		} catch(Exception e) {
			throw new InternalSeverException(INTERNAL_SERVER_STUDENT_ERROR);
		}
		LOG.info("Mapped students to teacher: {}", request.getTeacher());
		Response response = responseTransformer.getResponse(HttpStatus.NO_CONTENT.value(), DATA_SAVED);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
	
	
	/**
	 * unregister student from a teacher
	 * @param request
	 * @return
	 */
	@PutMapping(value = "/deregister", consumes = "application/json")
	public ResponseEntity<Response> removeTeachersStudents(@RequestBody Request request) {
		try {
			this.processValidation(requestValidation.validateRequest(request));
			boolean isDataExist = enrollmentService.unregisterStudentFromTeacher(request);
			if(!isDataExist) {
				throw new DataNotFoundException(DATA_NOT_FOUND_DEREGISTER_ERROR);
			}
		} catch(InvalidDataException ex) {
			throw new InvalidDataException(INVALID_DATA_ERROR);
		} catch(DataNotFoundException ex) {
			throw new DataNotFoundException(DATA_NOT_FOUND_DEREGISTER_ERROR);
		} catch(Exception e) {
			throw new InternalSeverException(INTERNAL_SERVER_STUDENT_ERROR);
		}
		LOG.info("Removed student: {} from teacher: {}", request.getStudent(), request.getTeacher());
		Response response = responseTransformer.getResponse(HttpStatus.OK.value(), DATA_REMOVED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * display common students for the list of teachers
	 * @param teacher
	 * @return
	 */
	@GetMapping(value = "/commonstudents", consumes = "application/json")
	public ResponseEntity<Response> getcommonstudentsForTeachers(@RequestParam List<String> teacher) {
		Response response = null;
		try {
			this.processValidation(requestValidation.validateEmailList(teacher));
			boolean isDataExist = teacherService.existsByEmails(teacher);
			if(!isDataExist) {
				throw new DataNotFoundException(DATA_NOT_FOUND);
			}
			response = teacherService.getcommonstudentsForTeachers(teacher);
		} catch(InvalidDataException ex) {
			throw new InvalidDataException(INVALID_DATA_ERROR);
		} catch(DataNotFoundException ex) {
			throw new DataNotFoundException(DATA_NOT_FOUND);
		} catch(Exception e) {
			throw new InternalSeverException(INTERNAL_SERVER_COMMON_ERROR);
		}
		LOG.info("Common students for the given teachers are retrieved");
		response.setInformation(responseTransformer.getResponseInformation(HttpStatus.OK.value(), DATA_RETRIEVED));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * displays list of teachers with their registered students
	 * @return
	 */
	@GetMapping(value = "/teachers", consumes = "application/json")
	public ResponseEntity<Response> getAllTeachers() {
		Response response = null;
		try {
			response = teacherService.getAllTeachers();
		} catch(Exception e) {
			throw new InternalSeverException(INTERNAL_SERVER_RETRIEVING_ERROR);
		}
		LOG.info("Retrieved all teachers with their registerd students");
		response.setInformation(responseTransformer.getResponseInformation(HttpStatus.OK.value(), DATA_RETRIEVED));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * throws Exception when the input validation fails
	 * @param isValid
	 */
	private void processValidation(boolean isValid) {
		if(!isValid) {
			LOG.error("Request Data validation is failed!!");
			throw new InvalidDataException(INVALID_DATA_ERROR);
		}
	}
	
}
