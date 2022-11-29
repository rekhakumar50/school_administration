package com.example.demo.exception;

public class DataNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = 7882258137142758019L;
	
	private String message;

	public DataNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
