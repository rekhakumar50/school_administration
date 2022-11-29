package com.example.demo.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.demo.constant.Constants.STRING_REGEX_PATTERN;

public class Utility {
	private static final Logger LOG = LoggerFactory.getLogger(Utility.class);


	/**
	 * Check the given email is valid or not
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(final String email) {
		/*
		 * return Pattern.compile(Constants.EMAIL_REGEX_PATTERN) .matcher(email)
		 * .matches();
		 */
		boolean isValid = false;
		if(StringUtils.isNotEmpty(email)) {
			isValid = EmailValidator.getInstance().isValid(email);
		}
		LOG.debug("Email validation: {}", isValid);
		return isValid;
	}
	
	
	/**
	 * Check the given name is valid or not
	 * @param name
	 * @return
	 */
	public static boolean validateName(final String name) {
		boolean isValid = false;
		if(StringUtils.isNotEmpty(name)) {
			isValid = Pattern.compile(STRING_REGEX_PATTERN)
							.matcher(name)
							.matches();
		}
		LOG.debug("Name validation: {}", isValid);
		return isValid;
	}

}
