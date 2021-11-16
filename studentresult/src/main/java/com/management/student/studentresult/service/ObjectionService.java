package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.*;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.ObjectionRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.vo.MarksVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectionService {

    @Autowired
    ObjectionRepository objectionRepository;

    @Autowired
    UserService userService;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    MarksRepository repository;

    public Objection raiseObjection(MarksVO marksVO){
        Objection objection = new Objection();
        User user = userService.getUserByExtId(marksVO.getRollNo());
        objection.setCreatedBy(user);
//        objection.setStatus("");
        Subject subject = subjectRepository.findBySubCode(marksVO.getSubjectCode());
        Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, marksVO.getTerm(), marksVO.getYear());
        objection.setMarks(mark);
        objectionRepository.save(objection);
        String response = "Objection raised Successfullly";
        return objection;
    }
}
