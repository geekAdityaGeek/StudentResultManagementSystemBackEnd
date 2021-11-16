package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.service.ModeratorService;
import com.management.student.studentresult.service.ObjectionService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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
        Objection objection;
        try {
            objection = objectionService.raiseObjection(marksVO);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Objection>(objection, HttpStatus.OK);
    }

    @RequestMapping(value = "/resolveObjection", method = RequestMethod.POST)
    public ResponseEntity<?> resolveRaisedObjection(@RequestBody List<ObjectionVO> objectionVOS) {
        List<ObjectionVO> objection;
        try {
            objection = objectionService.resolveObjection(objectionVOS);
        } catch (Exception ex) {
            String res = ex.getMessage();
            return new ResponseEntity<String>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ObjectionVO>>(objection, HttpStatus.OK);
    }
}
