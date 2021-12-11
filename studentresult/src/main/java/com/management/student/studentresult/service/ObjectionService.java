package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.*;
import com.management.student.studentresult.enums.Operation;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.ObjectionRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.PagingObjectionVO;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    UserRepository userRepository;
    @Autowired
    MarksRepository repository;


    public IObjectionServiceStrategy getUserType(String extId){
        IObjectionServiceStrategy userObj;
        if(userService.getUserByExtId(extId).getRole().equals("STUDENT"))
            userObj = new StudentObjectionServiceStrategy();
        else
            userObj = new ModeratorObjectionServiceStrategy();

        return userObj;
    }


    public List<Objection> raiseObjection(String extId, List<MarksVO> marksVOList) throws Exception{

        IObjectionServiceStrategy userStrategy = getUserType(extId);
        List<Objection> objectionList = userStrategy.raiseObjection(extId, marksVOList);
        return objectionList;
    }


    public List<ObjectionVO> resolveObjection(String extId, List<ObjectionVO> objectionVOS) throws Exception {
        IObjectionServiceStrategy userStrategy = getUserType(extId);
        List<ObjectionVO> objectionVOList = userStrategy.resolveObjection(extId, objectionVOS);
        return objectionVOList;
    }

    public PagingObjectionVO getObjections(String extId, Pageable pageable) throws Exception {
        IObjectionServiceStrategy userStrategy = getUserType(extId);
        return userStrategy.getObjections(extId, pageable);
    }

}
