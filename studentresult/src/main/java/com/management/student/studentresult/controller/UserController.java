package com.management.student.studentresult.controller;


import com.management.student.studentresult.service.RoleService;
import com.management.student.studentresult.service.UserService;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.management.student.studentresult.vo.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

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
