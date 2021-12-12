package com.management.student.studentresult.enums;

public enum HttpFields {
    REQ_PARAM_PAGE("page"),
    REQ_PARAM_ITEMS("items"),
    REQ_PARAM_EXTID("extId");

    private String name;

    HttpFields(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
