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

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;


    @PostMapping("/addUser")
    public UserDetails addUser(@RequestBody UserDetails userDetails) {

        Auth auth = new Auth(userDetails.getEmail(), userDetails.getPassword());
        authService.saveAuth(auth);
        Role role = roleService.getRoleById(userDetails.getRoleId());
        User user = new User(auth, role, userDetails.getRollNumber(), userDetails.getName(), userDetails.getAddress(), userDetails.getPhone(), userDetails.getDob());
        userService.saveUser(user);

        return userDetails;
    }
}
