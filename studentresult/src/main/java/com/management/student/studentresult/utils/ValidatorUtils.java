/**
 * 
 */
package com.management.student.studentresult.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.validator.ContactNumberFormatValidator;
import com.management.student.studentresult.validator.ContactNumberValidator;
import com.management.student.studentresult.validator.DateValidator;
import com.management.student.studentresult.validator.EmailExistsValidator;
import com.management.student.studentresult.validator.EmailFormatValidator;
import com.management.student.studentresult.validator.ExternalIdFormatValidator;
import com.management.student.studentresult.validator.ExternalIdNotExistsValidator;
import com.management.student.studentresult.validator.MarksExistsValidator;
import com.management.student.studentresult.validator.MarksValueValidator;
import com.management.student.studentresult.validator.ModeratorExistsValidator;
import com.management.student.studentresult.validator.StudentExistsValidator;
import com.management.student.studentresult.validator.SubcodeExistsValidator;
import com.management.student.studentresult.validator.TermExistsValidator;
import com.management.student.studentresult.validator.Validator;
import com.management.student.studentresult.validator.YearValidator;

/**
 * @author PRATAP
 *
 */
@Component
public class ValidatorUtils {

	@Autowired
	private StudentExistsValidator studentExistsValidator;

	@Autowired
	private ExternalIdFormatValidator externalIdFormatValidator;

	@Autowired
	private EmailFormatValidator emailFormatValidator;

	@Autowired
	private DateValidator dateValidator;

	@Autowired
	private MarksExistsValidator marksExistsValidator;

	@Autowired
	private MarksValueValidator marksValueValidator;

	@Autowired
	private ModeratorExistsValidator moderatorExistsValidator;

	@Autowired
	private SubcodeExistsValidator subcodeExistsValidator;

	@Autowired
	private TermExistsValidator termExistsValidator;

	@Autowired
	private YearValidator yearValidator;
	
	@Autowired
	private ContactNumberFormatValidator contactformatvalidator;
	
	@Autowired
	private ContactNumberValidator contactvalidator;
	
	@Autowired
	private EmailExistsValidator emailexistsvalidator;
	
	@Autowired
	private ExternalIdNotExistsValidator extidnotexistsvalidator;
	

	public static class ValidationFields {

		String extId;
		int year;
		int term;
		String subjCode;
		int totalMarks;
		Double marksObtained;
		String grade;
		String email;
		String contactno;
		Date date;
		public ValidationFields(String extId, int year, int term, String subjCode, int totalMarks, Double marksObtained,
				String grade) {
			super();
			this.extId = extId;
			this.year = year;
			this.term = term;
			this.subjCode = subjCode;
			this.totalMarks = totalMarks;
			this.marksObtained = marksObtained;
			this.grade = grade;
		}
		
		public ValidationFields(String extId, String email, String contactno, Date date) {
			super();
			this.extId = extId;
			this.email = email;
			this.contactno = contactno;
			this.date = date;
		}
		
		
	}

	public Validator validateChain(String operation, ValidationFields entity) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> map = FileFormatUtils.getFields(operation);
		Iterator<String> itr = map.keySet().iterator();
		Validator validate = null, temp = null;
		while (itr.hasNext()) {
			String validateField = itr.next();
			switch (validateField) {
			case "EXTID_FORMAT_VALIDATOR": {
				externalIdFormatValidator.setUserName(entity.extId);
				if (validate == null) {
					validate = externalIdFormatValidator;
					temp = validate;
				} else {
					temp = temp.addNext(externalIdFormatValidator);
				}
				break;
			}
			case "STUDENT_EXIST_VALIDATOR": {
				studentExistsValidator.setStudentid(entity.extId);
				if (validate == null) {
					validate = studentExistsValidator;
					temp = validate;
				} else {
					temp = temp.addNext(studentExistsValidator);
				}
				break;
			}
			case "YEAR_VALIDATOR": {
				yearValidator.setYear(entity.year);
				if (validate == null) {
					validate = yearValidator;
					temp = validate;
				} else {
					temp = temp.addNext(yearValidator);
				}
				break;
			}
			case "TERM_EXISTS_VALIDATOR": {
				termExistsValidator.setTerm(entity.term);
				if (validate == null) {
					validate = termExistsValidator;
					temp = validate;
				} else {
					temp = temp.addNext(termExistsValidator);
				}
				break;
			}
			case "SUB_CODE_VALIDATOR": {
				subcodeExistsValidator.setSubcode(entity.subjCode);
				if (validate == null) {
					validate = subcodeExistsValidator;
					temp = validate;
				} else {
					temp = temp.addNext(subcodeExistsValidator);
				}
				break;
			}
			case "MARKS_VAL_VALIDATOR": {
				marksValueValidator.setMarks(entity.marksObtained);
				marksValueValidator.setTotalMarks((double) entity.totalMarks);
				if (validate == null) {
					validate = marksValueValidator;
					temp = validate;
				} else {
					temp = temp.addNext(marksValueValidator);
				}
				break;
			}			
			case "CONTACT_FORMAT_VALIDATOR":{
				contactformatvalidator.setContactno(entity.contactno);
				if (validate == null) {
					validate = contactformatvalidator;
					temp = validate;
				} else {
					temp = temp.addNext(contactformatvalidator);
				}
				break;
			}
			case "CONTACT_EXISTS_VALIDATOR":{
				contactvalidator.setContact(entity.contactno);
				if (validate == null) {
					validate = contactvalidator;
					temp = validate;
				} else {
					temp = temp.addNext(contactvalidator);
				}
				break;
			}
			case "EMAIL_FORMAT_VALIDATOR":{
				emailFormatValidator.setEmail(entity.email);
				if (validate == null) {
					validate = emailFormatValidator;
					temp = validate;
				} else {
					temp = temp.addNext(emailFormatValidator);
				}
				break;
			}
			case "EMAIL_EXISTS_VALIDATOR":{
				emailexistsvalidator.setEmail(entity.email);
				if (validate == null) {
					validate = emailexistsvalidator;
					temp = validate;
				} else {
					temp = temp.addNext(emailexistsvalidator);
				}
				break;
			}
			case "EXTID_NOT_EXITS_VALIDATOR":{
				extidnotexistsvalidator.setExtId(entity.extId);
				if (validate == null) {
					validate = extidnotexistsvalidator;
					temp = validate;
				} else {
					temp = temp.addNext(extidnotexistsvalidator);
				}
				break;
			}
			case "DATE_VALIDATOR":{
				dateValidator.setDate(entity.date);
				if (validate == null) {
					validate = dateValidator;
					temp = validate;
				} else {
					temp = temp.addNext(dateValidator);
				}
				break;
			}
			default:
				throw new Exception("Validator Field Doesn't Exist");
			}
		}
		return validate;
	}

}
