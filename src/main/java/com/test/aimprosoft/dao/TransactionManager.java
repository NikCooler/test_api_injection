package com.test.aimprosoft.dao;

import java.util.concurrent.Callable;

/**
 *
 */
public interface TransactionManager {

    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception;

}
