package com.test.aimprosoft.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @param <T>
 */
public interface Saver<T> {

    public PreparedStatement setCondition(PreparedStatement ps, T entity) throws SQLException;

}
