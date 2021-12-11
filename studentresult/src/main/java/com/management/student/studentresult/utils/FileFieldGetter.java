/**
 * 
 */
package com.management.student.studentresult.utils;

import java.util.Iterator;
import java.util.Map;

import com.management.student.studentresult.file_utils.FileField;
import com.management.student.studentresult.file_utils.GradeField;
import com.management.student.studentresult.file_utils.MarksObtainedField;
import com.management.student.studentresult.file_utils.RollField;
import com.management.student.studentresult.file_utils.SubjectCodeField;
import com.management.student.studentresult.file_utils.TermField;
import com.management.student.studentresult.file_utils.TotalMarksField;
import com.management.student.studentresult.file_utils.YearField;

/**
 * @author PRATAP
 *
 */
public class FileFieldGetter {

	public static FileField getFields(String format) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> map = FileFormatUtils.getFields(format);
		Iterator<String> itr = map.keySet().iterator();
		FileField field = null, temp = null;
		while (itr.hasNext()) {
			String fld = itr.next();
			switch (fld) {
			case "STUDENT_ROLL_NO": {
				if (field == null) {
					field = new RollField();
					temp = field;
				} else {
					temp = temp.addNextField(new RollField());
				}
				break;
			}
			case "YEAR": {
				if (field == null) {
					field = new YearField();
					temp = field;
				} else {
					temp = temp.addNextField(new YearField());
				}
				break;
			}
			case "TERM": {
				if (field == null) {
					field = new TermField();
					temp = field;
				} else {
					temp = temp.addNextField(new TermField());
				}
				break;
			}
			case "SUBJECT_CODE": {
				if (field == null) {
					field = new SubjectCodeField();
					temp = field;
				} else {
					temp = temp.addNextField(new SubjectCodeField());
				}
				break;
			}
			case "TOTAL_MARKS": {
				if (field == null) {
					field = new TotalMarksField();
					temp = field;
				} else {
					temp = temp.addNextField(new TotalMarksField());
				}
				break;
			}
			case "MARKS_OBTAINED": {
				if (field == null) {
					field = new MarksObtainedField();
					temp = field;
				} else {
					temp = temp.addNextField(new MarksObtainedField());
				}
				break;
			}
			case "GRADE": {
				if (field == null) {
					field = new GradeField();
					temp = field;
				} else {
					temp = temp.addNextField(new GradeField());
				}
				break;
			}
			default:
				throw new Exception("Field not present in the format " + format);
			}
		}
		return field;
	}
}
