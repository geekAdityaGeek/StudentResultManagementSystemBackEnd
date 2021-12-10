package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.student.studentresult.repository.UserRepository;

public class StudentExistsValidator extends Validator {

	private String studentid;
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(!repo.existsByExtId(studentid))
			throw new Exception("Student Id:"+studentid+ " doesn't exist!");
	}
	
	public StudentExistsValidator(String studentid) {
		
		this.studentid = studentid;
	}
}
