package com.test.aimprosoft.dao.user;

import com.test.aimprosoft.dao.AbstractDao;
import com.test.aimprosoft.util.DataBaseException;
import com.test.aimprosoft.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String INSERT          = "INSERT INTO user (email, password) VALUES (?,?)";
    public static final String SELECT_BY_EMAIL = "SELECT email, password FROM user WHERE email=?";

    @Override
    public User selectByEmail(String email) throws DataBaseException {
        Connection conn = getSerializableConnection();
        PreparedStatement ps = null;
        ResultSet rs;
        UserSelector extractor = new UserSelector();
        try{
            ps = conn.prepareStatement(SELECT_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractor.selectOne(rs);
            }

        } catch (SQLException e){
            throw new DataBaseException("Can not execute query: "+SELECT_BY_EMAIL);
        }
        return null;
    }

    @Override
    public void insert(User user) throws DataBaseException {
       saveOrUpdate(INSERT, user, new UserSaver());
    }

    @Override
    public boolean isUserExist(String email) throws DataBaseException {
       return selectByEmail(email) != null;
    }
}
