package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.service.AuthService;
import com.management.student.studentresult.service.RoleService;
import com.management.student.studentresult.service.UserService;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.management.student.studentresult.vo.ResponseMessage;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;    
    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<ResponseMessage> registerUser(@RequestBody UserDetails userDetails){
    	
    	return userService.registrationService(userDetails);
    }

}
