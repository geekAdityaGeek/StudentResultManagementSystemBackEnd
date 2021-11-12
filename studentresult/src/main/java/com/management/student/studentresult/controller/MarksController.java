package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<MarksVO> queryMarks(@RequestBody QueryVO query){
        return marksService.getMarksDetailsWithoutPagination(query);
    }

    @GetMapping("/getMarks/pagination")
    public List<MarksVO> queryMarksPagination(@RequestBody QueryVO query, @RequestParam int page, @RequestParam int items){
        Pageable pageable = PageRequest.of(page, items, Sort.by("createdAt").descending());
        List<MarksVO> marksList = marksService.getMarksDetailsWithPagination(query, pageable);
        return marksList; 
    }
}
