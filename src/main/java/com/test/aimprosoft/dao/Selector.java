package com.test.aimprosoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Selector<T> {

    public abstract T selectOne(ResultSet rs) throws SQLException;

    public List<T> selectAll(ResultSet rs) throws SQLException{
        List<T> result = new ArrayList<T>(rs.getFetchSize());

        while (rs.next()){
            result.add(selectOne(rs));
        }
        return result;
    }

}
