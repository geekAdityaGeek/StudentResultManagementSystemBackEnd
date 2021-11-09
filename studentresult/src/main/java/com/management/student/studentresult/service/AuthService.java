package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository repository;

    public Auth saveAuth(Auth auth){
        return repository.save(auth);
    }
}
