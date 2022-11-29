package com.example.demo.exception;

public class DuplicateDataException extends RuntimeException {
	private static final long serialVersionUID = -3942069263507565163L;
	
	private String message;

	public DuplicateDataException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
