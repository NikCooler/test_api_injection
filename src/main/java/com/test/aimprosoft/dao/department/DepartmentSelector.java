package com.test.aimprosoft.dao.department;

import com.test.aimprosoft.dao.Selector;
import com.test.aimprosoft.entity.Department;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DepartmentSelector extends Selector<Department> {

    @Override
    public Department selectOne(ResultSet rs) throws SQLException {

        int id = rs.getInt("departmentId");
        String name = rs.getString("name");
        return new Department(id,name);
    }
}
