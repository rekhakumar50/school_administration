package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.constant.Constants.SERVER_UP;

@RestController
public class HealthCheckController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HealthCheckController.class);
	
	/**
	 * health check end-point
	 * @return
	 */
	@GetMapping("/healthCheck")
	public ResponseEntity<String> getHealthCheck() {
		LOG.info(SERVER_UP);
		return new ResponseEntity<>(SERVER_UP, HttpStatus.OK);
	}

}
