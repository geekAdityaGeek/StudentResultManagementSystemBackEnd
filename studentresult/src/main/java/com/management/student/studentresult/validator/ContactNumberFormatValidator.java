package com.management.student.studentresult.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ContactNumberFormatValidator extends Validator {

	private String contactno;
	private static final String contactPattern="^[0-9]{10}$";
	
	
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(contactPattern);
        Matcher m = p.matcher(contactno);
        if(!m.matches()){
            throw new Exception("The pattern of cantact number is invalid for: "+contactno);
        }

	}

}
