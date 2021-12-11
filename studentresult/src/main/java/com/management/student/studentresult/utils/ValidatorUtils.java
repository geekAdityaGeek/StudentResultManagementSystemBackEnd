/**
 * 
 */
package com.management.student.studentresult.utils;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.validator.DateValidator;
import com.management.student.studentresult.validator.EmailFormatValidator;
import com.management.student.studentresult.validator.ExternalIdFormatValidator;
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

	public static class ValidationFields {

		String extId;
		int year;
		int term;
		String subjCode;
		int totalMarks;
		Double marksObtained;
		String grade;
		User user;
		Subject subject;

		public ValidationFields(String extId, int year, int term, String subjCode, int totalMarks, Double marksObtained,
				String grade, User user, Subject subject) {
			super();
			this.extId = extId;
			this.year = year;
			this.term = term;
			this.subjCode = subjCode;
			this.totalMarks = totalMarks;
			this.marksObtained = marksObtained;
			this.grade = grade;
			this.user = user;
			this.subject = subject;
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
			case "MARKS_EXISTS_VALIDATOR" : {
				marksExistsValidator.setSubject(entity.subject);
				marksExistsValidator.setUser(entity.user);
				marksExistsValidator.setTerm(entity.term);
				marksExistsValidator.setYear(entity.year);
				
				break;
			}
			default:
				throw new Exception("Validator Field Doesn't Exist");
			}
		}
		return validate;
	}

}
