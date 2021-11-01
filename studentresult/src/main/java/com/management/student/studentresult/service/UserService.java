package com.management.student.studentresult.service;

import com.management.student.studentresult.vo.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {

    public UserDetails getUserDeatilsByRoll(String rollNumber) throws ParseException {
        //This method will fetch the details based on roll
        //for now sample dummy implementation has been provided
        UserDetails userDetails = new UserDetails();
        userDetails.setName("ADITYA SAHA");
        userDetails.setRollNumber("MT2020093");
        userDetails.setAddress("C-9/8 Dankuni Housing Estate, Dankuni, HHooghly, WB, Pin 712311");
        userDetails.setPhone("9007295089");
        userDetails.setEmail("adi96saha@gmail.com");
        userDetails.setDob(new SimpleDateFormat("yyyy-MM-dd").parse("1996-10-20"));
        return userDetails;
    }
}
