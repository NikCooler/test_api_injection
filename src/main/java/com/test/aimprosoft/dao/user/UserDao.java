package com.test.aimprosoft.dao.user;

import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.User;

/**
 *
 */
public interface UserDao {

     public User selectByEmail(String email) throws DataBaseException;
     public boolean isUserExist(String email) throws DataBaseException;
     public void insert(User user) throws DataBaseException;


}
