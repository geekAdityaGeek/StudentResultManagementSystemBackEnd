package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.RoleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleActionRepository extends JpaRepository<RoleAction, Integer> {
}
