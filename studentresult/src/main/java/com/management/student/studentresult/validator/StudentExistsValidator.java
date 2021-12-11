package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.repository.UserRepository;

@Component
public class StudentExistsValidator extends Validator {

	private String studentid;
	
	@Autowired
	private UserRepository repo;

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}	

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if (!repo.existsByExtId(studentid))
			throw new Exception("Student Id:" + studentid + " doesn't exist!");
	}
	
}
