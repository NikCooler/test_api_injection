package com.test.aimprosoft.dao.employee;

import com.test.aimprosoft.dao.Dao;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Employee;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {

    public List<Employee> selectEmployeesOfDepartment(int departmentId) throws DataBaseException;

}
