package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.utils.QueryVOMapper;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @GetMapping("/getMarks")
    public List<MarksVO> queryMarks(@RequestBody QueryVO query){
        return marksService.getMarksDetailsWithoutPagination(query);
    }

    @GetMapping("/getMarks/pagination")
    public List<MarksVO> queryMarksPagination(@RequestParam Map<String, String> requestParameters){
        int page = Integer.parseInt(requestParameters.get("page"));
        int items = Integer.parseInt(requestParameters.get("items"));
        QueryVO query = QueryVOMapper.mapFromRequestParameter(requestParameters);
        Pageable pageable = PageRequest.of(page, items, Sort.by("year").descending().by("term").descending());
        List<MarksVO> marksList = marksService.getMarksDetailsWithPagination(query, pageable);
        return marksList; 
    }

    @GetMapping("/marks")
    public List<MarksVO> queryMarksOnParam(@RequestParam Map<String, String> requestParameters){
        QueryVO query = QueryVOMapper.mapFromRequestParameter(requestParameters);
        return marksService.getMarksDetailsWithoutPagination(query);
    }
}
