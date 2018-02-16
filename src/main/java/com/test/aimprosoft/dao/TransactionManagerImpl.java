package com.test.aimprosoft.dao;

import com.test.aimprosoft.util.JdbcUtils;

import java.sql.Connection;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: ENERGIZER
 * Date: 10.10.13
 * Time: 0:26
 * To change this template use File | Settings | File Templates.
 */
public class TransactionManagerImpl implements TransactionManager {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
    private static final ConnectionPoolC3P0 connectionC3P0 = new ConnectionPoolC3P0();

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        Connection connection = connectionC3P0.getConnection();
        connectionHolder.set(connection);
        try{
            T result = unitOfWork.call();
            connection.commit();
            return  result;
        }catch (Exception e){
            connection.rollback();
            throw e;
        }finally {
            JdbcUtils.closeQuietly(connection);
            connectionHolder.remove();

        }
    }
    public static Connection getConnection(){
        return  connectionHolder.get();
    }
}
