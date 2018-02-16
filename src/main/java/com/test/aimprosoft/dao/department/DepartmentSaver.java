package com.test.aimprosoft.dao.department;

import com.test.aimprosoft.dao.Saver;
import com.test.aimprosoft.entity.Department;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class DepartmentSaver implements Saver<Department> {

    @Override
    public PreparedStatement setCondition(PreparedStatement ps, Department department) throws SQLException {
        ps.setString(1, department.getName());
        return ps;
    }
}
