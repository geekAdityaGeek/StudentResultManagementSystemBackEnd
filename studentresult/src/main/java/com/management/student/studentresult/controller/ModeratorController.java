/**
 * 
 */
package com.management.student.studentresult.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.management.student.studentresult.service.ModeratorService;

import java.util.List;

/**
 * @author PRATAP
 *
 */
@RestController
@RequestMapping("/moderator")
@CrossOrigin
public class ModeratorController {

	@Autowired
	private ModeratorService moderatorService;

	@RequestMapping(value = "/bulkUpload", method = RequestMethod.POST)
	public ResponseEntity<?> marksBulkUpload(@RequestParam(name = "file", required = true) MultipartFile fileMarksUpl) {
		String response = "";
		try {
			response = moderatorService.marksBulkUpload(fileMarksUpl);
		} catch (Exception ex) {
			response = ex.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getUniqueTerms", method = RequestMethod.GET)
	public ResponseEntity<?> uniqueTerms() {
		List<String> response ;
		try {
			response = moderatorService.getTerms();
		} catch (Exception ex) {
			String res = ex.getMessage();
			return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(response, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/getListSubjCodeName", method = RequestMethod.GET)
	public ResponseEntity<?> subjCodeName() {
		List<String> response ;
		try {
			response = moderatorService.getSubjUseCodeName();
		} catch (Exception ex) {
			String res = ex.getMessage();
			return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<String>>(response, HttpStatus.ACCEPTED);
	}

}
