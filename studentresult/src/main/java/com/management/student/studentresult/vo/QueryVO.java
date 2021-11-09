package com.management.student.studentresult.vo;

public class QueryVO {
    private String rollNumber;
    private int term;
    private int year;

    public QueryVO() {
    }

    public QueryVO(String rollNumber, int term, int year) {
        this.rollNumber = rollNumber;
        this.term = term;
        this.year = year;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
