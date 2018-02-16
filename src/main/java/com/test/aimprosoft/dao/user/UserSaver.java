package com.test.aimprosoft.dao.user;

import com.test.aimprosoft.dao.Saver;
import com.test.aimprosoft.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class UserSaver implements Saver<User> {

        @Override
        public PreparedStatement setCondition(PreparedStatement ps, User user) throws SQLException {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            return ps;
        }
    }

