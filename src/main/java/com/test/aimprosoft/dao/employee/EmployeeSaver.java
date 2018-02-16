package com.test.aimprosoft.dao.employee;

import com.test.aimprosoft.dao.Saver;
import com.test.aimprosoft.entity.Employee;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EmployeeSaver implements Saver<Employee> {

    @Override
    public PreparedStatement setCondition(PreparedStatement ps, Employee employee) throws SQLException {
        ps.setString(1, employee.getFirstname());
        ps.setString(2, employee.getLastname());
        java.util.Date dateOfBirth =  employee.getDateOfBirth();
        ps.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
        ps.setInt(4, employee.getAbsenteeism());
        ps.setInt(5, employee.getDepartment().getdepartmentId());
        return ps;
    }
}
