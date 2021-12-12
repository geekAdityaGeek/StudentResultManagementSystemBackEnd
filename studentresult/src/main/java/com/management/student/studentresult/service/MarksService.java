package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.enums.Operation;
import com.management.student.studentresult.enums.Status;
import com.management.student.studentresult.repository.MarksRepository;
import com.management.student.studentresult.repository.ObjectionRepository;
import com.management.student.studentresult.repository.SubjectRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.specs.MarksSpecs;
import com.management.student.studentresult.utils.Constants;
import com.management.student.studentresult.utils.ValidatorUtils;
import com.management.student.studentresult.validator.Validator;
import com.management.student.studentresult.vo.MarksVO;
import com.management.student.studentresult.vo.PagingMarksVO;
import com.management.student.studentresult.vo.QueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service
public class MarksService {

    @Autowired
    private MarksRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ObjectionRepository objectionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorUtils validatorUtils;

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
        Specification spec5 = MarksSpecs.marksStatusActive();
        Specification querySpec = Specification.where(spec1).and(spec2).and(spec3).and(spec4).and(spec5);

        List<Marks> marksList = repository.findAll(querySpec, Sort.by("createdAt").ascending());

        for(Marks marks : marksList){
            MarksVO marksVO = new MarksVO(marks.getUser().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
            marksVOList.add(marksVO);
        }

        return marksVOList;
    }

    public PagingMarksVO getMarksDetailsWithPagination(QueryVO queryVO, Pageable pageable) {

        List<MarksVO> marksVOList= new ArrayList<>();

        Subject subject = subjectRepository.findBySubCode(queryVO.getSubjectCode());
        User user = userRepository.findByExtId(queryVO.getRollNumber());
        Specification spec1 = MarksSpecs.marksSubjectEquals(subject, queryVO.getSubjectCode());
        Specification spec2 = MarksSpecs.marksUserEquals(user, queryVO.getRollNumber());
        Specification spec3 = MarksSpecs.marksTermEquals(queryVO.getTerm());
        Specification spec4 = MarksSpecs.marksYearEquals(queryVO.getYear());
        Specification spec5 = MarksSpecs.marksStatusActive();
        Specification querySpec = Specification.where(spec1).and(spec2).and(spec3).and(spec4).and(spec5);

        Page<Marks> marksList = repository.findAll(querySpec, pageable);

        for(Marks marks : marksList){
            MarksVO marksVO = new MarksVO(marks.getUser().getExtId(), marks.getSubject().getSubCode(), marks.getSubject().getName(), marks.getYear(), marks.getTerm(), marks.getTotScore(), marks.getScore(), marks.getGrade());
            marksVOList.add(marksVO);
        }

        return new PagingMarksVO(pageable.getPageNumber(), marksList.getTotalPages(), pageable.getPageSize(), marksVOList);
    }


    public List<MarksVO> updateMarksQueryResult(List<MarksVO> marksVO) throws Exception {
        List<MarksVO> marksVOList = new ArrayList<>();
        for (MarksVO marksVO1 : marksVO) {
            String operationType = marksVO1.getOperation();
            String operation = Constants.FEILD_CONFIGURATION_KEY_UPDATE_VALIDATION;
            User user = userService.getUserByExtId(marksVO1.getRollNo());
            Subject subject = subjectRepository.findBySubCode(marksVO1.getSubjectCode());
            ValidatorUtils.ValidationFields validationFields = new ValidatorUtils.ValidationFields(
                    marksVO1.getRollNo(), marksVO1.getYear(), marksVO1.getTerm(),
                    marksVO1.getSubjectCode(), marksVO1.getTotalMarks(),
                    marksVO1.getMarksObtained(), marksVO1.getGrade(), user, subject);
            Validator validator = validatorUtils.validateChain(Constants.FEILD_CONFIGURATION_KEY_UPDATE_VALIDATION, validationFields);
            validator.validate();
            Marks mark = repository.findByUserAndSubjectAndTermAndYear(user, subject, marksVO1.getTerm(), marksVO1.getYear());
            if(Operation.DELETE.getName().equals(operationType)) {
                MarksVO temp = performSoftDelete(mark, marksVO1, subject);
                marksVOList.add(temp);
            }else if(Operation.UPDATE.getName().equals(operationType)) {
                MarksVO temp = performUpdate(mark, marksVO1, subject);
                marksVOList.add(temp);
            }
        }
        return marksVOList;
    }

    public MarksVO performSoftDelete(Marks marks, MarksVO marksVO, Subject subject){
        marks.setStatus(Status.INACTIVE.getName());
        marks = repository.save(marks);
        Objection objection = objectionRepository.findByMarks(marks);
        if(objection != null) {
            objection.setStatus(Status.INACTIVE.getName());
            objectionRepository.save(objection);
        }
        MarksVO temp = new MarksVO(marksVO.getRollNo(), subject.getSubCode(), subject.getName(),
                marksVO.getYear(), marksVO.getTerm(), marks.getTotScore(),
                marks.getScore(), marks.getGrade(),
                marks.getStatus());
        return temp;
    }

    public MarksVO performUpdate(Marks mark, MarksVO markVO, Subject subject){
        mark.setScore(markVO.getMarksObtained());
        mark.setGrade(markVO.getGrade());
        mark.setTotScore(markVO.getTotalMarks());
        mark = repository.save(mark);
        MarksVO temp = new MarksVO(markVO.getRollNo(), subject.getSubCode(), subject.getName(),
                markVO.getYear(), markVO.getTerm(), mark.getTotScore(), mark.getScore(), mark.getGrade(),
                mark.getStatus());
        return temp;
    }


}
