package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.repository.AuthRepository;

@Component
public class EmailExistsValidator extends Validator{

	private String email;
	
	@Autowired
	private AuthRepository repo;
	
	
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(repo.existsByEmail(email))
			throw new Exception("This email "+email+" already exists!");
	}
	
	
}
