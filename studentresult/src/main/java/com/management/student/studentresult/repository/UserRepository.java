package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByExtId(String extId);

}
