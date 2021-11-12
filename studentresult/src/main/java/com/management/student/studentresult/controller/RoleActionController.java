package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleActionController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){

//        Role newrole = new Role(role.getName(), role.getCreatedBy(), role.getModifiedBy());
        return roleService.saveRole(role);
    }

//    @GetMapping("/allRoles")
//    public List<Role> allRoles(){
//        return roleService.getRoles();
//    }

}
