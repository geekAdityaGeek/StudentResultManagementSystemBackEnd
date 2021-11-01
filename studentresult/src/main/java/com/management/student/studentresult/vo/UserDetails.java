package com.management.student.studentresult.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private String name;
    private String rollNumber;
    private Date dob;
    private String address;
    private String phone;
    private String email;


}
