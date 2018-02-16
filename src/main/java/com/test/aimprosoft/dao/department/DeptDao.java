package com.test.aimprosoft.dao.department;


import com.test.aimprosoft.dao.Dao;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Department;

public interface DeptDao extends Dao<Department> {

    public Department selectByName(String name) throws DataBaseException;

}
