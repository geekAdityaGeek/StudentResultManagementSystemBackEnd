package com.management.student.studentresult.validator;

public abstract class Validator implements ValidatorChain {
    private Validator nextValidator;
    public abstract void validateEntity() throws Exception;
    public void addNext(Validator validator){
        this.nextValidator = validator;
    }
    @Override
    public void validate() throws Exception {
        validateEntity();
        if(nextValidator!=null)
        	nextValidator.validate();
    }
}
