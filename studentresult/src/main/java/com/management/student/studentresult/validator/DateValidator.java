package com.management.student.studentresult.validator;

import java.util.Date;

public class DateValidator extends Validator {

	
	private Date date;
	
	public DateValidator(Date date) {
		
		this.date = date;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Date presentdate = new Date();
		if(presentdate.after(new Date()))
			throw new Exception("Future dates are invalid!");
	}

}
