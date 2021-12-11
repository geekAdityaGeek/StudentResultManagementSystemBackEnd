package com.management.student.studentresult.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalIdFormatValidator extends Validator {


    private static final String userNamePattern= "(IMT|MT|PH|DT|MOD)[0-9]{7}";
    private String userName;

    public ExternalIdFormatValidator(String userName) {
		
		this.userName = userName;
	}
	private void validateUsernameFormat(String userName) throws Exception {

        Pattern p = Pattern.compile(userNamePattern);
        Matcher m = p.matcher(userName);
        if(!m.matches()){
            throw new Exception("Username pattern doesnot matches");
        }
    }
    @Override

    public void validateEntity() throws Exception {
        try{
            validateUsernameFormat(userName);
        }catch(Exception e){
            throw new Exception("Inavlid Username Provided: "+userName);
        }
    }


}
