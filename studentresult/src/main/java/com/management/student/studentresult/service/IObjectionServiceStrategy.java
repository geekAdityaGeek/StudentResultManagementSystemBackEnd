package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.ObjectionVO;
import com.management.student.studentresult.vo.PagingObjectionVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IObjectionServiceStrategy {
    public List<Objection> raiseObjection(List<MarksVO> marksVOList) throws Exception;
    public List<ObjectionVO> resolveObjection(List<ObjectionVO> objectionVOS) throws Exception;
    public PagingObjectionVO getObjections(String extId, Pageable pageable) throws Exception;

}
