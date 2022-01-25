package com.revature.repos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO{

    @Override
    public User getUserByUsername(String username) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT ers_password FROM ers_users WHERE ers_username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                User user = new User();
                user.setUsername(result.getString("ers_username"));
                user.setPassword(result.getString("ers_password"));

                return user;
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(User o) {
        return false;
    }
    @Override
    public boolean update(User o) {
        return false;
    }
    @Override
    public boolean delete(int id) {
        return false;
    }
    @Override
    public List<User> getAll() {
        return null;
    }
    @Override
    public User get(int id) {
        return null;
    }
}
