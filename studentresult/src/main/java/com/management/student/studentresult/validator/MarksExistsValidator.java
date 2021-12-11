package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;

@Component
public class MarksExistsValidator extends Validator {

	private User user;
	private Subject subject;
	private int term, year;

	public void setUser(User user) {
		this.user = user;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setRepo(MarksRepository repo) {
		this.repo = repo;
	}

	@Autowired
	private MarksRepository repo;

	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if (!repo.existsByUserAndSubjectAndTermAndYear(user, subject, term, year))
			throw new Exception("Marks corresponding to userId: " + user.getExtId() + " subjectcode: "
					+ subject.getSubCode() + " term: " + term + " year: " + year + " doesn't exist!");
	}

}
