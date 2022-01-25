package com.revature.repos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO{

    // USED BY MANAGER AND EMPLOYEE TO LOGIN
    @Override
    public User login(String username) {
        try (Connection conn = ConnectionUtil.getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}