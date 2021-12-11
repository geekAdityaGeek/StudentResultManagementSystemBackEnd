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
public class TotalMarksField extends FileField {

	@Override
	public void handleNext(Map<String, Integer> map, Row row) throws Exception {
		// TODO Auto-generated method stub
		try {
			Double totMarks = row.getCell(map.get("TOTAL_MARKS")).getNumericCellValue();
			marksVO.setTotalMarks(totMarks.intValue());
			if (next != null)
				next.handleNext(map, row);
		} catch (Exception e) {
			throw new Exception("");
		}
	}

}
