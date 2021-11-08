package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Integer> {
}
