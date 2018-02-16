package com.test.aimprosoft.dao.user;

import com.test.aimprosoft.dao.Selector;
import com.test.aimprosoft.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserSelector extends Selector<User> {

      @Override
      public User selectOne(ResultSet rs) throws SQLException {

         String email = rs.getString("email");
         String password = rs.getString("password");

         return new User(email, password);
      }
}
