package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.repository.SubjectRepository;

@Component
public class SubcodeExistsValidator extends Validator {

	private String subcode;

	@Autowired
	private SubjectRepository repo;

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public void setRepo(SubjectRepository repo) {
		this.repo = repo;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if (!repo.existsBySubCode(subcode))
			throw new Exception("Subcode: " + subcode + " doesn't exist!");
	}

}
