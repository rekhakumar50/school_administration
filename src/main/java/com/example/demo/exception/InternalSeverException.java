package com.example.demo.exception;

public class InternalSeverException extends RuntimeException {
	private static final long serialVersionUID = -8097903343387300709L;
	
	private String message;

	public InternalSeverException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
