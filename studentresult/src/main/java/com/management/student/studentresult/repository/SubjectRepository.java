package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findBySubCode(String subCode);
}
