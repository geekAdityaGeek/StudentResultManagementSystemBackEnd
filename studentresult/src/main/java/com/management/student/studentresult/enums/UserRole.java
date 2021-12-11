package com.management.student.studentresult.enums;

public enum UserRole {

    STUDENT("STUDENT"),
    MODERATOR("MODERATOR");

    private String name;

    UserRole(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
