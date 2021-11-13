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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    @Autowired
    private UserService userService;

    public Marks saveMarks(Marks marks){
        return repository.save(marks);
    }

    public List<MarksVO> getMarksDetailsWithoutPagination(QueryVO queryVO) {
        List<MarksVO> marksVOList= new ArrayList<>();

        Subject subject = subjectRepository.findBySubCode(queryVO.getSubjectCode());
        User user = userRepository.findByExtId(queryVO.getRollNumber());
        Specification spec1 = MarksSpecs.marksSubjectEquals(subject, queryVO.getSubjectCode());
        Specification spec2 = MarksSpecs.marksUserEquals(user, queryVO.getRollNumber());
        Specification spec3 = MarksSpecs.marksTermEquals(queryVO.getTerm());
        Specification spec4 = MarksSpecs.marksYearEquals(queryVO.getYear());
        Specification querySpec = Specification.where(spec1).and(spec2).and(spec3).and(spec4);

        List<Marks> marksList = repository.findAll(querySpec, Sort.by("createdAt").ascending());

        for(Marks marks : marksList){
            MarksVO marksVO = new MarksVO(marks.getUser().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
            marksVOList.add(marksVO);
        }

        return marksVOList;
    }

    public List<MarksVO> getMarksDetailsWithPagination(QueryVO queryVO, Pageable pageable) {

        List<MarksVO> marksVOList= new ArrayList<>();

        Subject subject = subjectRepository.findBySubCode(queryVO.getSubjectCode());
        User user = userRepository.findByExtId(queryVO.getRollNumber());
        Specification spec1 = MarksSpecs.marksSubjectEquals(subject, queryVO.getSubjectCode());
        Specification spec2 = MarksSpecs.marksUserEquals(user, queryVO.getRollNumber());
        Specification spec3 = MarksSpecs.marksTermEquals(queryVO.getTerm());
        Specification spec4 = MarksSpecs.marksYearEquals(queryVO.getYear());
        Specification querySpec = Specification.where(spec1).and(spec2).and(spec3).and(spec4);

        Page<Marks> marksList = repository.findAll(querySpec, pageable);

        for(Marks marks : marksList){
            MarksVO marksVO = new MarksVO(marks.getUser().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
            marksVOList.add(marksVO);
        }

        return marksVOList;
    }

    public List<MarksVO> updateMarksQueryResult(List<MarksVO> marksVO) throws ParseException {
        List<MarksVO> marksVOList = new ArrayList<>();
        for (MarksVO marksVO1 : marksVO) {
            String operationType = marksVO1.getUpdateDeleteStatus();
            User user = userService.getUserById(Integer.parseInt(marksVO1.getRollNo()));
            Subject subject = subjectRepository.findBySubCode(marksVO1.getSubjectCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, marksVO1.getTerm(), marksVO1.getYear());
            MarksVO temp ;
            if (operationType.equals("delete")) {
                mark.setStatus("INACTIVE");
                mark = repository.save(mark);
                temp = new MarksVO(marksVO1.getRollNo(), marksVO1.getSubjectCode(), marksVO1.getSubjectName(),
                        marksVO1.getYear(), marksVO1.getTerm(), mark.getTotScore(), mark.getScore(), mark.getGrade(),
                        mark.getStatus());
                marksVOList.add(temp);
            }
            else {
                mark.setScore(marksVO1.getMarksObtained());
                mark.setGrade(marksVO1.getGrade());
                mark.setTotScore(marksVO1.getTotalMarks());
                mark = repository.save(mark);
                temp = new MarksVO(marksVO1.getRollNo(), marksVO1.getSubjectCode(), marksVO1.getSubjectName(),
                        marksVO1.getYear(), marksVO1.getTerm(), mark.getTotScore(), mark.getScore(), mark.getGrade(),
                        mark.getStatus());
                marksVOList.add(temp);
            }
        }
        return marksVOList;
    }
}
