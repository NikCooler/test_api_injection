package com.test.aimprosoft.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.test.aimprosoft.util.DataBaseException;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class ConnectionPoolC3P0 {

    private final ComboPooledDataSource dataSource;
    {
        try{
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dept");
            dataSource.setUser("root");
            dataSource.setPassword("");
            dataSource.setMaxStatements(50);
            dataSource.setMinPoolSize(1);
            dataSource.setAcquireIncrement(2);
            dataSource.setMaxPoolSize(10);

        }catch (PropertyVetoException e){
            throw new RuntimeException("Configuration C3P0 Exception");
        }
    }
    public Connection getConnection() throws DataBaseException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DataBaseException("Can not create connection");
        }
    }

}
