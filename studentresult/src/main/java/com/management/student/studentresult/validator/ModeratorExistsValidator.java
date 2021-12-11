package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.repository.UserRepository;

@Component
public class ModeratorExistsValidator extends Validator {

	private String moderatorid;

	@Autowired
	private UserRepository repo;

	public void setModeratorid(String moderatorid) {
		this.moderatorid = moderatorid;
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if (!repo.existsByExtId(moderatorid))
			throw new Exception("Moderator Id:" + moderatorid + "doesn't exist!");

	}

}
