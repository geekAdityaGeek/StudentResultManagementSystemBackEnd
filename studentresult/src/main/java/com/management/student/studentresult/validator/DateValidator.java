package com.management.student.studentresult.validator;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateValidator extends Validator {

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Date presentdate = new Date();
		if (presentdate.after(new Date()))
			throw new Exception("Future dates are invalid!");
	}

}
