package com.management.student.studentresult.validator;

public class TermExistsValidator extends Validator {

	private int term;
	
	
	public TermExistsValidator(int term) {
		
		this.term = term;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		if(term<0 || term>2)
			throw new Exception("Term: "+term + " is invalid");
	}

}
