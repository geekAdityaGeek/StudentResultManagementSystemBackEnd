package com.management.student.studentresult.enums;

public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
