package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.ObjectionRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.PagingObjectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentObjectionServiceStrategy implements IObjectionServiceStrategy{
    @Autowired
    ObjectionRepository objectionRepository;
    @Autowired
    UserService userService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MarksRepository repository;

    @Override
    public List<Objection> raiseObjection(String extId,List<MarksVO> marksVOList) throws Exception {
        List<Objection> objectionList = new ArrayList<>();
        for (MarksVO marksVO : marksVOList) {
            Objection objection = new Objection();
            User user = userService.getUserByExtId(marksVO.getRollNo());
            objection.setCreatedBy(user);
            objection.setStatus("ACTIVE");
            Subject subject = subjectRepository.findBySubCode(marksVO.getSubjectCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYearAndStatus(user, subject, marksVO.getTerm(), marksVO.getYear(), "ACTIVE");
            if (checkIfObjectionExists(mark))
                throw new Exception("Student has already raised the exception for this mark record!");
            objection.setMarks(mark);
            if (mark.getModifiedBy() != null) {
                objection.setResolverId(mark.getModifiedBy());
                objection.setModifiedBy(user);
            }
            objectionRepository.save(objection);
            String response = "Objection raised Successfullly";
        }
        return objectionList;
    }

    @Override
    public List<ObjectionVO> resolveObjection(String extId, List<ObjectionVO> objectionVOS) throws Exception {
        throw new Exception("Student not allowed to resolve exception");
    }

    @Override
    public PagingObjectionVO getObjections(String extId, Pageable pageable){
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        User user = userService.getUserByExtId(extId);
        Page<Objection> objections = objectionRepository.findAllByCreatedBy(user, pageable);
        for (Objection objection : objections) {
            if(objection.getStatus().equals("INACTIVE"))
                continue;
            ObjectionVO objectionVO = new ObjectionVO();
            objectionVO.setComments(objection.getComment());
            objectionVO.setOperation(objection.getStatus());
            objectionVO.setGrade(objection.getMarks().getGrade());
            objectionVO.setRollNo(objection.getCreatedBy().getExtId());
            objectionVO.setYear(objection.getMarks().getYear());
            objectionVO.setTerm(objection.getMarks().getTerm());
            objectionVO.setSubjectCode(objection.getMarks().getSubject().getSubCode());
            objectionVO.setTotalMarks(objection.getMarks().getTotScore());
            objectionVO.setMarksObtained(objection.getMarks().getScore());
            objectionVO.setSubjectName(objection.getMarks().getSubject().getName());
            objectionVOList.add(objectionVO);
        }

        return new PagingObjectionVO(pageable.getPageNumber(), objections.getTotalPages(), pageable.getPageSize(), objectionVOList);
    }

    public boolean checkIfObjectionExists(Marks mark) {
        Objection objection = objectionRepository.findByMarks(mark);
        if (objection == null)
            return false;
        return true;
    }
}
