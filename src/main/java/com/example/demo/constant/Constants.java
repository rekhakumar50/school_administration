package com.example.demo.constant;

public class Constants {
	
	public static final String SERVER_UP = "Server is UP..";

	public static final String EMAIL_REGEX_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
	public static final String STRING_REGEX_PATTERN = "[a-zA-Z]+";
	
	public static final String DATA_EXIST_ERROR = "Data Already Exist";
	public static final String INVALID_DATA_ERROR = "Data passed in the request is invalid";
	public static final String DATA_NOT_FOUND = "Teachers Data not found";
	public static final String DATA_NOT_FOUND_REGISTER_ERROR = "Student or Teacher Data not found / Students Already Register to the Teacher";
	public static final String DATA_NOT_FOUND_DEREGISTER_ERROR = "Student or Teacher Data not found / Student Already De-Register to the Teacher";
	public static final String INTERNAL_SERVER_STUDENT_ERROR = "Error While adding student Data in DB";
	public static final String INTERNAL_SERVER_TEACHER_ERROR = "Error While adding teacher Data in DB";
	public static final String INTERNAL_SERVER_MAPPING_ERROR = "Error While mapping students to teacher";
	public static final String INTERNAL_SERVER_REMOVE_ERROR = "Error While removing student from teacher";
	public static final String INTERNAL_SERVER_COMMON_ERROR = "Error While retrieving common students of teachers";
	public static final String INTERNAL_SERVER_RETRIEVING_ERROR = "Error While retrieving all teachers";

	public static final String DATA_SAVED = "Data Saved";
	public static final String DATA_REMOVED = "Data Removed";
	public static final String DATA_RETRIEVED = "Retrieved all Data";
	
	public static final String REGISTERED = "Registered";
	public static final String DEREGISTERED = "Deregistered";

}
