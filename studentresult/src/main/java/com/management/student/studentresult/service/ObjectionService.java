package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.*;
import com.management.student.studentresult.enums.Operation;
import com.management.student.studentresult.enums.UserRole;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service
public class ObjectionService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentObjectionServiceStrategy studentObjectionServiceStrategy;

    @Autowired
    private ModeratorObjectionServiceStrategy moderatorObjectionServiceStrategy;


    public IObjectionServiceStrategy getUserType(String extId) throws Exception {
        IObjectionServiceStrategy userObj = null;
        if(UserRole.STUDENT.getName().equals(userService.getUserByExtId(extId).getRole().getName()))
            userObj = studentObjectionServiceStrategy;
        else if (UserRole.MODERATOR.getName().equals(userService.getUserByExtId(extId).getRole().getName()))
            userObj = moderatorObjectionServiceStrategy;
        else
            throw new Exception("Invalid role for the user");
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
