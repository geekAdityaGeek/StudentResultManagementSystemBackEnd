package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
	public Subject findBySubCode(String subCode);

	@Query("SELECT DISTINCT c.term FROM Subject c")
	public List<String> findDistinctByTerm();

	@Query(value = "SELECT c.sub_code, c.name FROM subject c" , nativeQuery = true)
	public List<String> findSubjectByCodeName();

}
