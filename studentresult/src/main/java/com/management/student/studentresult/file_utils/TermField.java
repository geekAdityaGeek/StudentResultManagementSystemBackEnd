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
public class TermField extends FileField {

	@Override
	public void handleNext(Map<String, Integer> map, Row row) throws Exception {
		// TODO Auto-generated method stub
		try {
			Double term = row.getCell(map.get("TERM")).getNumericCellValue();
			marksVO.setTerm(term.intValue());
			if (next != null)
				next.handleNext(map, row);
		} catch (Exception e) {
			throw new Exception("");
		}
	}

}
