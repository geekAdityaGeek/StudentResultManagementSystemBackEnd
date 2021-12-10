package com.management.student.studentresult.validator;

public class MarksValueValidator extends Validator{

    private Double totalMarks;
    private Double marks;


    public MarksValueValidator(Double totalMarks, Double marks) {
		
		this.totalMarks = totalMarks;
		this.marks = marks;
	}

	@Override
    public void validateEntity() throws Exception {

        if(marks < 0){

            throw new Exception("Marks cannot be negative");
        }
        if(totalMarks != null && marks>totalMarks){
            throw new Exception("Marks cannot be more than total marks");
        }
    }
}