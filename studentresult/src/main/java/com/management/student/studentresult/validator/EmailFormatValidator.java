package com.management.student.studentresult.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailFormatValidator extends Validator {

	private static final Pattern emailPattern = Pattern.compile("^.+@iiitb.org$");
	private String email;

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Matcher m = emailPattern.matcher(email);
		if (!m.matches())
			throw new Exception(email + " is not valid!");
	}

}
