package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class HealthCheckControllerTest {
	
	@InjectMocks
	private HealthCheckController healthCheckController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getHealthCheck() {
        ResponseEntity<String> responseEntity = healthCheckController.getHealthCheck();
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
}
