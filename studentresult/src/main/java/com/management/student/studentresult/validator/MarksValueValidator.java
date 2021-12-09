package com.management.student.studentresult.validator;

public class MarksValueValidator extends Validator{

    private Double totalMarks = null;
    private Double marks = null;


    public void setTotalMarks(double totalMarks){
        this.totalMarks = totalMarks;
    }

    @Override
    public void validateEntity(Object entity) throws Exception {

        if(marks < 0){
            throw new Exception("Marks cannot be negative");
        }
        if(totalMarks != null && marks>totalMarks){
            throw new Exception("Marks cannot be more than total marks");
        }
    }

    @Override
    public void checkType(Object entity) throws Exception {
        if(!(entity instanceof Double)){
            throw new Exception("Marks is not of valid type");
        }
    }

    @Override
    protected void setValue(Object entity) {
        this.marks = (Double)entity;
    }
}