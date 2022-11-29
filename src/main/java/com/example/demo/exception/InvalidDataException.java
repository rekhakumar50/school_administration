package com.example.demo.exception;

public class InvalidDataException extends RuntimeException {
	private static final long serialVersionUID = -3942069263507565163L;
	
	private String message;

	public InvalidDataException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
