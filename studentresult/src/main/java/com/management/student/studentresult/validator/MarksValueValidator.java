package com.management.student.studentresult.validator;

import org.springframework.stereotype.Component;

@Component
public class MarksValueValidator extends Validator {

	private Double totalMarks;
	private Double marks;

	public void setTotalMarks(Double totalMarks) {
		this.totalMarks = totalMarks;
	}

	public void setMarks(Double marks) {
		this.marks = marks;
	}

	@Override
	public void validateEntity() throws Exception {

		if (marks < 0) {

			throw new Exception("Marks cannot be negative");
		}
		if (totalMarks != null && marks > totalMarks) {
			throw new Exception("Marks cannot be more than total marks");
		}
	}

}