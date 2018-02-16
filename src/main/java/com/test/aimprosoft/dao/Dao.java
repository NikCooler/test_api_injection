package com.test.aimprosoft.dao;


import com.test.aimprosoft.util.DataBaseException;

import java.util.List;

public interface Dao<T> {

    public List<T> selectAll() throws DataBaseException;
    public void deleteById(int id) throws DataBaseException;
    public void insert(T entity) throws DataBaseException;
    public T selectById(int id) throws DataBaseException;
    public void update(T entity)throws DataBaseException;
}
