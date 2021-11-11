/**
 * 
 */
package com.management.student.studentresult.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.repository.UserRepository;

/**
 * @author PRATAP
 *
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class ModeratorService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MarksRepository marksRepository;

	public String marksBulkUpload(MultipartFile fileMarksUpl) throws Exception {
		// TODO Auto-generated method stub
		String response = "";
		XSSFWorkbook workbook = null;
		InputStream stream = fileMarksUpl.getInputStream();
		workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowItr = sheet.iterator();
		while (rowItr.hasNext()) {
			Row row = rowItr.next();
			if (row.getRowNum() == 0)
				continue;
			String rollNo = row.getCell(0).getStringCellValue();
			Double year = row.getCell(2).getNumericCellValue();
			Double term = row.getCell(1).getNumericCellValue();
			String subjectCode = row.getCell(3).getStringCellValue();
			Double totalMarks = row.getCell(4).getNumericCellValue();
			Double marksObtained = row.getCell(5).getNumericCellValue();
			String grade = row.getCell(6).getStringCellValue();
			System.out.println(rollNo + " " + term + " " + year + " " + subjectCode + " " + totalMarks + " "
					+ marksObtained + " " + grade);
			User student = userRepository.findByExtId(rollNo);
			Subject subject = subjectRepository.findBySubCode(subjectCode);
			if (student == null || subject == null) {
				throw new Exception("Student or Subject not found. Please check and update the file.");
			}
			Marks mark = new Marks(student, subject, marksObtained, totalMarks.intValue(), term.intValue(),
					year.intValue(), grade);
			marksRepository.save(mark);
		}
		response = "Marks successfully uploaded";

		if (workbook != null)
			workbook.close();

		return response;
	}

}
