package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.AuthRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user){
        return repository.save(user);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public UserDetails getUserDetailsByRoll(String rollNumber) throws ParseException {

        User user = repository.findByExtId(rollNumber);

        UserDetails userDetails = new UserDetails();
        userDetails.setName(user.getName());
        userDetails.setRollNumber(user.getExtId());
        userDetails.setAddress(user.getAddress());
        userDetails.setPhone(user.getPhone());
        userDetails.setEmail(user.getAuth().getEmail());

        return userDetails;
    }

}
