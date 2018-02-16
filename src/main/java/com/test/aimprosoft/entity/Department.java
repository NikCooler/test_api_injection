package com.test.aimprosoft.entity;


public class Department {

    public Integer departmentId;
    public String  name;

    public Department(){
    }

    public Department(Integer departmentId){
        this.departmentId = departmentId;
    }

    public Department(Integer departmentId, String name){
        this.departmentId = departmentId;
        this.name = name;
    }

}
