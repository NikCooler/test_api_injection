package com.test.aimprosoft.bean;

import com.test.aimprosoft.entity.Department;

public class EmployeeBean {

    public Integer    employeeId;
    public String     firstName;
    public String     lastName;
    public String     dateOfBirth;
    public String     absenteeism;
    public Department department;


    public EmployeeBean(String firstName, String lastName, String dateOfBirth, String absenteeism, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.absenteeism = absenteeism;
        this.department = department;
    }
}
