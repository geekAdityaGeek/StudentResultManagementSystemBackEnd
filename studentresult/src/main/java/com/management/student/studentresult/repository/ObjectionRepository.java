package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectionRepository extends JpaRepository<Objection, Integer> {

    Objection findByMarks(Marks marks);

    List<Objection> findAllByCreatedBy(User user);

    List<Objection> findAllByResolverId(User user);
}
