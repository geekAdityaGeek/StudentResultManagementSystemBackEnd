package com.management.student.studentresult.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YearValidator extends Validator {

	private int year;
	
	
	public YearValidator(int year) {
		
		this.year = year;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = format.parse(format.format(new Date()));
    	SimpleDateFormat df = new SimpleDateFormat("yyyy");
    	int year = Integer.parseInt(df.format(date));
    	System.out.println(year);
		if(this.year > year || this.year < 1900)
			throw new Exception("Year: "+year+" is invalid!");
	}

}
