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
public class RollField extends FileField {

	@Override
	public void handleNext(Map<String, Integer> map, Row row) throws Exception {
		// TODO Auto-generated method stub
		try {
			// fetching
			String roll = row.getCell(map.get("STUDENT_ROLL_NO")).getStringCellValue();
			marksVO.setRollNo(roll);
			if (next != null)
				next.handleNext(map, row);
		} catch (Exception e) {
			throw new Exception("");
		}
	}

}
