package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.repository.UserRepository;

@Component
public class ContactNumberValidator extends Validator {

	private String contact;
	
	@Autowired
	private UserRepository repo;
	
	
	public void setContact(String contact) {
		this.contact = contact;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(repo.existsByPhone(contact))
			throw new Exception("The contact number "+contact+" is already taken");
	}

}
