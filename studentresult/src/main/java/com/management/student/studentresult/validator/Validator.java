package com.management.student.studentresult.validator;

public abstract class Validator implements ValidatorChain {
    private Validator nextValidator;
    public abstract void validateEntity(Object entity) throws Exception;
    protected abstract void checkType(Object entity) throws Exception;
    protected abstract void setValue(Object entity);
    public void addNext(Validator validator){
        this.nextValidator = validator;
    }
    public void validate(Object entity) throws Exception {
        checkType(entity);
        setValue(entity);
        validateEntity(entity);
    }
}
