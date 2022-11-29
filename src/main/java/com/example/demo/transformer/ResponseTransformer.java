package com.example.demo.transformer;

import org.springframework.stereotype.Component;

import com.example.demo.dto.Response;
import com.example.demo.dto.ResponseInformation;

@Component
public class ResponseTransformer {
	
	
	/**
	 * Populate Response Object for the given status code and message
	 * @param code
	 * @param message
	 * @return
	 */
	public Response getResponse(int code, String message) {
		Response response = new Response();
		response.setInformation(this.getResponseInformation(code, message));
		return response;
	}
	
	
	/**
	 * Populate ResponseInformation Object for the given status code and message
	 * @param code
	 * @param message
	 * @return
	 */
	public ResponseInformation getResponseInformation(int code, String message) {
		ResponseInformation responseInformation = new ResponseInformation();
		responseInformation.setCode(code);
		responseInformation.setMessage(message);
	
		return responseInformation;
	}

}
