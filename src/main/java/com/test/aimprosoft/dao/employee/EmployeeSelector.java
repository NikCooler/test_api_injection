package com.test.aimprosoft.dao.employee;

import com.test.aimprosoft.dao.Selector;
import com.test.aimprosoft.entity.Department;
import com.test.aimprosoft.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSelector extends Selector<Employee> {

    @Override
    public Employee selectOne(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("employee_id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("date_of_birth"),
                rs.getInt("absenteeism"),
                new Department(
                        rs.getInt("department_id"),
                        rs.getString("name")
                )
        );
    }
}
