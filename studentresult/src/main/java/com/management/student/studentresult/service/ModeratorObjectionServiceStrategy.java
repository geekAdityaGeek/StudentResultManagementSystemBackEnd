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
public class ModeratorObjectionServiceStrategy implements IObjectionServiceStrategy{
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
    public List<Objection> raiseObjection(String extId, List<MarksVO> marksVOList) throws Exception {
        throw new Exception("Moderator not allowed to raise objection");
    }

    @Override
    public List<ObjectionVO> resolveObjection(String extId, List<ObjectionVO> objectionVOS) throws Exception {
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        User moderator = userService.getUserByExtId(extId);
        for (ObjectionVO objection : objectionVOS) {
            User user = userService.getUserByExtId(objection.getRollNo());
            Subject subject = subjectRepository.findBySubCode(objection.getSubjectCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, objection.getTerm(), objection.getYear());
            Objection obj = objectionRepository.findByMarks(mark);
            obj.setComment(objection.getComments());
            //obj.setResolverId(mark.getCreatedBy());
            if (objection.getOperation().equals("RESOLVED"))
                obj.setStatus("RESOLVED");
            else
                obj.setStatus("REJECTED");
            obj.setModifiedBy(moderator);
            objectionRepository.save(obj);
            objectionVOList.add(objection);

        }
        return objectionVOList;
    }

    @Override
    public PagingObjectionVO getObjections(String extId, Pageable pageable){
        List<ObjectionVO> objectionVOList = new ArrayList<>();
        User user = userService.getUserByExtId(extId);
        Page<Objection> objections = objectionRepository.findAllByResolverId(user, pageable);
        for (Objection objection : objections) {
            if (objection.getStatus().equals("INACTIVE"))
                continue;
            ObjectionVO objectionVO = new ObjectionVO();
            Subject subject = subjectRepository.findBySubCode(objection.getMarks().getSubject().getSubCode());
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, objection.getMarks().getTerm(), objection.getMarks().getYear());
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
}
