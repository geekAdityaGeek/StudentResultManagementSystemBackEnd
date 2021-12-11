/**
 * 
 */
package com.management.student.studentresult.file_utils;

import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author PRATAP
 *
 */
public class SubjectCodeField extends FileField {

	@Override
	public void handleNext(Map<String, Integer> map, Row row) throws Exception {
		// TODO Auto-generated method stub
		try {
			String subjCode = row.getCell(map.get("SUBJECT_CODE")).getStringCellValue();
			marksVO.setSubjectCode(subjCode);
			if (next != null)
				next.handleNext(map, row);
		} catch (Exception e) {
			throw new Exception("");
		}
	}

}
