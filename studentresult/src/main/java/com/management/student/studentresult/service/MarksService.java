package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.specs.MarksSpecs;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public Marks saveMarks(Marks marks){
        return repository.save(marks);
    }

    public List<MarksVO> getMarksDetailsWithoutPagenation(QueryVO queryVO) {
        List<MarksVO> marksVOList= new ArrayList<>();

        Subject subject = subjectRepository.findBySubCode(queryVO.getSubjectCode());
        User user = userRepository.findByExtId(queryVO.getRollNumber());
        Specification spec1 = MarksSpecs.marksSubjectEquals(subject);
        Specification spec2 = MarksSpecs.marksUserEquals(user);
        Specification spec3 = MarksSpecs.marksTermEquals(queryVO.getTerm());
        Specification spec4 = MarksSpecs.marksYearEquals(queryVO.getYear());
        Specification querySpec = Specification.where(spec1).and(spec2).and(spec3).and(spec4);

        List<Marks> marksList = repository.findAll(querySpec);

        for(Marks marks : marksList){
            MarksVO marksVO = new MarksVO(marks.getUser().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
            marksVOList.add(marksVO);
        }

        return marksVOList;
    }
}
