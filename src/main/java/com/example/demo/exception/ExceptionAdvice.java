package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.Response;
import com.example.demo.transformer.ResponseTransformer;

@RestControllerAdvice
public class ExceptionAdvice {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@Autowired
	private ResponseTransformer responseTransformer;
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST) 
	public ResponseEntity<Response>  handleDuplicateDataException(DuplicateDataException e) { 
		LOG.error("The given data is exist in DB");
		Response response = responseTransformer.getResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	}
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> handleDataNotFoundException(DataNotFoundException e) {
		LOG.error("Data not found in DB");
		Response response = responseTransformer.getResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);	
	}
	
	
	@ExceptionHandler({InternalSeverException.class, Exception.class})
	public ResponseEntity<Response> handleInternalSeverException(InternalSeverException e) {
		LOG.error("Exception occured while processing data");
		Response response = responseTransformer.getResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler()
	@ResponseStatus(value = HttpStatus.BAD_REQUEST) 
	public ResponseEntity<Response>  handleInvalidDataException(InvalidDataException e) { 
		LOG.error("Data passed is not valid");
		Response response = responseTransformer.getResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	}
	
}
