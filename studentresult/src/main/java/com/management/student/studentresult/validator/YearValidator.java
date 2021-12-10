package com.management.student.studentresult.validator;

import java.util.Date;

public class YearValidator extends Validator {

	private int year;
	
	
	public YearValidator(int year) {
		
		this.year = year;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Date presentdate = new Date();
		if(year > presentdate.getYear())
			throw new Exception("Future years are invalid!");
	}

}
