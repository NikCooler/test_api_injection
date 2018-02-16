package com.test.aimprosoft.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    private Integer employeeId;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private Integer absenteeism;
    private Department department;
    private String dateAsString;

    public Employee(){

    }

    public Employee(Integer employeeId, String firstname, String lastname, Date dateOfBirth, Integer absenteeism, Department department) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.absenteeism = absenteeism;
        this.department = department;
    }

    public Employee(String firstname, String lastname, Date dateOfBirth, Integer absenteeism, Department department) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.absenteeism = absenteeism;
        this.department = department;
    }

    public Integer getemployeeId() {
        return employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getDateAsString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(getDateOfBirth());
    }

    public Integer getAbsenteeism() {
        return absenteeism;
    }

    public Department getDepartment() {
        return department;
    }

}
