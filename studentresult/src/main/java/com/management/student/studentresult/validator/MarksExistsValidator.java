package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;

public class MarksExistsValidator extends Validator {

	private User user;
	private Subject subject;
	private int term, year;
	
	@Autowired
	private MarksRepository repo;

	public MarksExistsValidator(User user, Subject subject, int term, int year) {
		
		this.user = user;
		this.subject = subject;
		this.term = term;
		this.year = year;
	}

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if (repo.existsByUserAndSubjectAndTermAndYear(user, subject, term, year))
			throw new Exception("Marks corresponding to userId: " + user.getExtId() + " subjectcode: "
					+ subject.getSubCode() + " term: " + term + " year: " + year + " doesn't exist!");
	}

}
