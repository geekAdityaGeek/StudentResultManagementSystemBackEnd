package com.management.student.studentresult.service;

import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarksService {

    public List<MarksVO> getMarksDetailsWithoutPagenation(QueryVO queryVO) {
        List<MarksVO> marksVOList= new ArrayList<>();
        int year = 2018;
        int term = 1;
        for(int i=1;i<=16;i++){
            if(i%8==0){
                year++;
                term=0;
            }
            if(i%4==0){
                term++;
            }
            MarksVO marks1 = new MarksVO();
            marks1.setRollNo("MT2020093");
            marks1.setSubjectCode("A01");
            marks1.setSubjectName("Subject 1");
            marks1.setMarksObtained(95);
            marks1.setTotalMarks(100);
            marks1.setGrade("A+");
            marks1.setYear(year);
            marks1.setTerm(term);
            marksVOList.add(marks1);
        }

        return marksVOList;
    }
}
