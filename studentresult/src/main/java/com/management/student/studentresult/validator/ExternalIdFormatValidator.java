package com.management.student.studentresult.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalIdFormatValidator extends Validator {

    private static final String userNamePattern = "";
    private String userName = null;

    private void validateUsernameFormat(String userName) throws Exception {
        Pattern p = Pattern.compile(userNamePattern);
        Matcher m = p.matcher(userName);
        if(!m.matches()){
            throw new Exception("Username pattern doesnot matches");
        }
    }
    @Override
    public void validateEntity(Object entity) throws Exception {
        try{
            validateUsernameFormat(userName);
        }catch(Exception e){
            throw new Exception("Inavlid Username Provided");
        }
    }

    @Override
    protected void checkType(Object entity) throws Exception {
        if(!(entity instanceof String)){
            throw new Exception("Username is not of valid type");
        }
    }

    @Override
    protected void setValue(Object entity) {
        userName = (String)entity;
    }
}
