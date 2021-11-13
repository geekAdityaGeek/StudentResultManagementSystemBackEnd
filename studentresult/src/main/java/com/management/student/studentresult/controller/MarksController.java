package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.utils.QueryVOMapper;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MarksController {

    @Autowired
    private MarksService marksService;

    @GetMapping("/getMarks")
    public List<MarksVO> queryMarks(@RequestBody QueryVO query){
        return marksService.getMarksDetailsWithoutPagenation(query);
    }

    @GetMapping("/marks")
    public List<MarksVO> queryMarksOnParam(@RequestParam Map<String, String> requestParameters){
        QueryVO query = QueryVOMapper.mapFromRequestParameter(requestParameters);
        return marksService.getMarksDetailsWithoutPagenation(query);
    }
}
