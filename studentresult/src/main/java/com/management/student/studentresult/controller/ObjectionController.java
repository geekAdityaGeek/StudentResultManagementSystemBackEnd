package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.service.ModeratorService;
import com.management.student.studentresult.service.ObjectionService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/objection")
@CrossOrigin("*")
public class ObjectionController {

    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private MarksService marksService;

    @Autowired
    private ObjectionService objectionService;

    @RequestMapping(value = "/raiseObjection", method = RequestMethod.POST)
    public ResponseEntity<?> studentRaiseObjection(@RequestBody MarksVO marksVO) {
        System.out.println("nnnn");
        Objection objection;
        try {
            //check
            objection = objectionService.raiseObjection(marksVO);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Objection>(objection, HttpStatus.OK);
    }
}
