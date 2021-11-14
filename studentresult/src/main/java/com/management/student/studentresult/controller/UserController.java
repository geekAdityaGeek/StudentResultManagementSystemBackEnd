package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.service.AuthService;
import com.management.student.studentresult.service.AuthenticationService;
import com.management.student.studentresult.service.RoleService;
import com.management.student.studentresult.service.UserService;
import com.management.student.studentresult.vo.LoginCredentials;
import com.management.student.studentresult.vo.ResponseMessage;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){

        Role newrole = new Role(role.getName(), role.getCreatedBy(), role.getModifiedBy());
        return roleService.saveRole(newrole);
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<ResponseMessage> registerUser(@RequestBody UserDetails userDetails){
    	
    	return userService.registrationService(userDetails);
    }

    @GetMapping("/allRoles")
    public @ResponseBody ResponseEntity<?>  allRoles() throws Exception{
    	List<String> roleNamesList= new ArrayList<String>();
		List<Role> rolesList=roleService.getRoles();
		for(int i=0;i<rolesList.size();i++)
			roleNamesList.add(rolesList.get(i).getName());
        return new ResponseEntity<List<String>>(roleNamesList,HttpStatus.OK);
    }
    
    @PostMapping("/authenticate")
    public @ResponseBody ResponseEntity<ResponseMessage> authenticateUser(@RequestBody LoginCredentials credentials) {
    	
    	return authService.authenticate(credentials);
    }

}
