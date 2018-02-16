package com.test.aimprosoft.dao.employee;

import com.test.aimprosoft.dao.AbstractDao;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Employee;

import java.util.List;

public class EmployeeDaoImpl extends AbstractDao<Employee> implements EmployeeDao {

    public static final  String SELECT_ALL_EMPLOYEE_SQL = "SELECT d.department_id, d.name, e.employee_id, e.first_name, e.lastname, e.date_of_birth, e.absenteeism\n" +
            "FROM department d INNER JOIN employee e ON d.department_id = e.department_department_id";

    public static final  String SELECT_EMPLOYEES_OF_DEPARTMENT_SQL = "SELECT d.department_id, d.name, e.employee_id, e.first_name, e.lastname, e.date_of_birth, e.absenteeism\n" +
            "FROM employee e INNER JOIN department d ON e.department_department_id=d.department_id\n" +
            "WHERE d.department_id=";

    public static final  String DELETE_EMPLOYEE_BY_ID_SQL = "DELETE FROM employee WHERE employee_id=";
    public static final  String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (first_name, lastname, date_of_birth, absenteeism, department_department_id) VALUES (?,?, ?,?, ?)";
    public static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET first_name=?, lastname=?, date_of_birth=?, absenteeism=?, department_department_id=? WHERE employee_id=";

    public static final String SELECT_EMPLOYEE_BY_ID = "SELECT d.department_id, d.name, e.employee_id, e.first_name, e.lastname, e.date_of_birth, e.absenteeism\n" +
            "FROM employee e INNER JOIN department d ON e.department_department_id=d.department_id\n" +
            "WHERE e.employee_id=";

    @Override
    public List<Employee> selectAll() throws DataBaseException {
        return selectAll(SELECT_ALL_EMPLOYEE_SQL, new EmployeeSelector());
    }

    @Override
    public Employee selectById(int id) throws DataBaseException {
        return selectById(SELECT_EMPLOYEE_BY_ID + id, new EmployeeSelector());
    }

    @Override
    public List<Employee> selectEmployeesOfDepartment(int departmentId) throws DataBaseException {
        return selectAll(SELECT_EMPLOYEES_OF_DEPARTMENT_SQL + departmentId,new EmployeeSelector());
    }

    @Override
    public void deleteById(int employeeId) throws DataBaseException {
        deleteById(DELETE_EMPLOYEE_BY_ID_SQL + employeeId);
    }
    
    @Override
    public void insert (Employee employee) throws DataBaseException {
        saveOrUpdate(INSERT_EMPLOYEE_SQL, employee, new EmployeeSaver());
    }

    @Override
    public void update(Employee employee) throws DataBaseException {
        saveOrUpdate(UPDATE_EMPLOYEE_SQL+employee.getemployeeId(), employee, new EmployeeSaver());
    }

}
