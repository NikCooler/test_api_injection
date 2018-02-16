package com.test.aimprosoft.dao.department;

import com.test.aimprosoft.dao.AbstractDao;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DeptDaoImpl extends AbstractDao<Department> implements DeptDao {

    public static final String SELECT_ALL     = "SELECT departmentId, name FROM department";
    public static final String SELECT_BY_ID   = "SELECT departmentId, name FROM department WHERE departmentId=";
    public static final String SELECT_BY_NAME = "SELECT departmentId, name FROM department WHERE name=?";
    public static final String DELETE_BY_ID   = "DELETE FROM department WHERE departmentId=";
    public static final String INSERT         = "INSERT INTO department (name) VALUES (?)";
    public static final String UPDATE_BY_ID   = "UPDATE department SET name=? WHERE departmentId=";

    @Override
    public List<Department> selectAll() throws DataBaseException {
        return selectAll(SELECT_ALL, new DepartmentSelector());
    }

    @Override
    public Department selectById(int id) throws DataBaseException {
        return selectById(SELECT_BY_ID+id, new DepartmentSelector());
    }

    @Override
    public void deleteById(int departmentId) throws DataBaseException {
        deleteById(DELETE_BY_ID+departmentId);
    }

    @Override
    public void insert(Department department) throws DataBaseException {
          saveOrUpdate(INSERT, department, new DepartmentSaver());
    }

    @Override
    public void update(Department department) throws DataBaseException {
        saveOrUpdate(UPDATE_BY_ID  +department.departmentId, department, new DepartmentSaver());
    }

    @Override
    public Department selectByName(String name) throws DataBaseException {
        Connection conn = getSerializableConnection();
        ResultSet rs;
        PreparedStatement ps;
        DepartmentSelector selector = new DepartmentSelector();
        try {
            ps = conn.prepareStatement(SELECT_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if(rs.next()){
                return selector.selectOne(rs);
            }
        } catch (SQLException e){
            throw new DataBaseException("Can not execute query: " + SELECT_BY_NAME);
        }
        return null;
    }
}
