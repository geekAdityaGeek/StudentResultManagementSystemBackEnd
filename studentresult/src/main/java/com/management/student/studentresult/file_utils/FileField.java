/**
 * 
 */
package com.management.student.studentresult.file_utils;

import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.management.student.studentresult.vo.MarksVO;

/**
 * @author PRATAP
 *
 */
public abstract class FileField implements FileFieldsChain {

	protected FileField next;
	
	public static MarksVO marksVO = new MarksVO();

	@Override
	public FileField addNextField(FileField field) {
		// TODO Auto-generated method stub
		this.next = field;
		return this.next;
	}

	public abstract void handleNext(Map<String, Integer> map, Row row) throws Exception;

}
