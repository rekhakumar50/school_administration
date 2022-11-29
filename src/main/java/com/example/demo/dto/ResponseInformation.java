package com.example.demo.dto;

import java.io.Serializable;

public class ResponseInformation implements Serializable {
	private static final long serialVersionUID = 2923790425028989848L;
	
	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
