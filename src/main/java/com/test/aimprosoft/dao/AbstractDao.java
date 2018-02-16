package com.test.aimprosoft.dao;

import com.test.aimprosoft.util.DataBaseException;

import java.sql.*;
import java.util.List;

/**
 *
 */
public abstract class AbstractDao<T> {

    protected Connection getConnection() throws DataBaseException {
        Connection connection = TransactionManagerImpl.getConnection();
        if(connection == null){
            throw new IllegalStateException("Coonnection = null");
        }
        return connection;
    }

    protected Connection getSerializableConnection() throws DataBaseException {
        try{
            Connection result = getConnection();
            result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            result.setAutoCommit(false);
            return result;
        }catch (SQLException e){
            throw new DataBaseException("Can't create connection");
        }
    }

    protected List<T> selectAll(String sql, Selector<T> selector) throws DataBaseException {
        Connection conn = getSerializableConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);

            return selector.selectAll(rs);
        }catch (SQLException e){
            throw new DataBaseException("Can not excecute selectAll");
        }

    }
    protected T selectById(String sql, Selector<T> selector) throws DataBaseException {
        Connection conn = getSerializableConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            if(rs.next()){
                return selector.selectOne(rs);
            }


        }catch (SQLException e){
            throw new DataBaseException("Can not excecute selectById");
        }
        return null;
    }
    protected void deleteById(String sql) throws DataBaseException {
        Connection conn = getSerializableConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(sql);

        }catch (SQLException e){
            throw new DataBaseException("Can not execute deleteById");
        }

    }

    protected void saveOrUpdate(String sql, T entity, Saver<T> saveOrUpdate) throws DataBaseException {
        Connection conn = getSerializableConnection();
        PreparedStatement ps;
        try{
            ps = conn.prepareStatement(sql);
            ps = saveOrUpdate.setCondition(ps, entity);
            ps.executeUpdate();

        } catch (SQLException e){
            throw new DataBaseException("Can not execute query: "+sql);
        }
    }
}
