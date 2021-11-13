package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarksController {

    @Autowired
    private MarksService marksService;

    @GetMapping("/getMarks")
    public ResponseEntity<?> queryMarks(@RequestBody QueryVO query){
        try {
            List<MarksVO> response =  marksService.getMarksDetailsWithoutPagination(query);
            if(response.size() == 0 )
                throw new Exception("No marks found !");
            return new ResponseEntity<List<MarksVO>>(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getMarks/pagination")
    public ResponseEntity<?> queryMarksPagination(@RequestBody QueryVO query, @RequestParam int page, @RequestParam int items){
        try {
            Pageable pageable = PageRequest.of(page, items, Sort.by("createdAt").descending());
            List<MarksVO> response = marksService.getMarksDetailsWithPagination(query, pageable);
            if(response.size() == 0 )
                throw new Exception("No marks found !");
            return new ResponseEntity<List<MarksVO>>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
