package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.student.studentresult.repository.UserRepository;

public class ModeratorExistsValidator extends Validator{

	
	private String moderatorid;
	
	@Autowired
	private UserRepository repo;
	
	public ModeratorExistsValidator(String moderatorid) {
		
		this.moderatorid = moderatorid;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(!repo.existsByExtId(moderatorid))
			throw new Exception("Moderator Id:"+ moderatorid + "doesn't exist!");
		
	}

}
