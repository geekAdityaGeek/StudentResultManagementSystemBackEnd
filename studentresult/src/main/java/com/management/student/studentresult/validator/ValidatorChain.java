package com.management.student.studentresult.validator;

public interface ValidatorChain {

    public void validate() throws Exception;

    public void addNext(Validator validator);
}
