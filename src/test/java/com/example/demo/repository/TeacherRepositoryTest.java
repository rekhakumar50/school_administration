package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.dao.Teacher;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TeacherRepositoryTest {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	
	@Test
	public void testSaveStudent() {
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("xxx@gmail.com");
		teacher.setName("xxx");
		
		Teacher teacherRes = teacherRepository.save(teacher);
		assertThat(teacherRes).isNotNull();		
	}
	
	
	@Test
	public void testExistById() {
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("xxx@gmail.com");
		teacher.setName("xxx");
		
		teacherRepository.save(teacher);
		boolean isExist = teacherRepository.existsById("xxx@gmail.com");
		assertThat(isExist).isTrue();		
	}
	
	
	@Test
	public void testFindById() {
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("xxx@gmail.com");
		teacher.setName("xxx");
		
		teacherRepository.save(teacher);
		Optional<Teacher> teacherRes = teacherRepository.findById("xxx@gmail.com");
		assertThat(teacherRes).isNotNull();		
	}
	
	
	@Test
	public void testFindAllTeacherEmail() {
		Teacher teacher = new Teacher();
		teacher.setTeacherEmail("teacherken@gmail.com");
		teacher.setName("ken");
		teacherRepository.save(teacher);
		
		List<String> teacherEmails = teacherRepository.findAllTeacherEmail();
		assertThat(teacherEmails).isNotNull();
		assertThat(teacherEmails.contains("teacherken@gmail.com")).isTrue();
	}

}
