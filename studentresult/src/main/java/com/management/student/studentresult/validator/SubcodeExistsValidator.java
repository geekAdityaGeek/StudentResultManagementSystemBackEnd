package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.student.studentresult.repository.SubjectRepository;

public class SubcodeExistsValidator extends Validator{

	private String subcode;
	
	@Autowired
	private SubjectRepository repo;
	
	public SubcodeExistsValidator(String subcode) {
		
		this.subcode = subcode;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(!repo.existsBySubCode(subcode))
			throw new Exception("Subcode: "+subcode + " doesn't exist!");
	}

}
