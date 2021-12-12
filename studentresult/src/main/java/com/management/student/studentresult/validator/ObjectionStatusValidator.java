package com.management.student.studentresult.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.enums.Operation;
import com.management.student.studentresult.repository.ObjectionRepository;

public class ObjectionStatusValidator extends Validator {

private Marks marks;
	
	@Autowired
	private ObjectionRepository repo;
	
	
	public void setMarks(Marks marks) {
		this.marks = marks;
	}


	@Override
	public void validateEntity() throws Exception {
		// TODO Auto-generated method stub
		Objection obj=repo.findByMarks(marks);
		if(obj==null)
			throw new Exception("objection doesn't exists!");
		if(!Operation.RESOLVE.getName().equals(obj.getStatus()) && !Operation.REJECTED.getName().equals(obj.getStatus()))
			throw new Exception("Objection status is neither reject or resolve!"); 
			
	}



}
