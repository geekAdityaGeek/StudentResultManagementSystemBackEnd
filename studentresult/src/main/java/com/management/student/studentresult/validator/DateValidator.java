package com.management.student.studentresult.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateValidator extends Validator {

	private Date date;
	
	
	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Date presentdate = new Date();
		SimpleDateFormat ft= new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(ft.format(date));
		Date prevDate=ft.parse("1889-12-31");
		System.out.println(!presentdate.after(date) +" "+ date.before(prevDate));
		if (!presentdate.after(date) || date.before(prevDate))
			throw new Exception("Invalid dates!");
	}

}
