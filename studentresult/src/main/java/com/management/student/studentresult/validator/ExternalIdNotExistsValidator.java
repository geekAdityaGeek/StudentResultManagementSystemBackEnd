package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.repository.UserRepository;

@Component
public class ExternalIdNotExistsValidator extends Validator{
	
	private String extId;
	
	@Autowired
	private UserRepository repo;

	
	public void setExtId(String extId) {
		this.extId = extId;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(repo.existsByExtId(extId))
			throw new Exception("This id "+extId+" already exists!");
	}
	
	

}
