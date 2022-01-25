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

            String sql = "SELECT * FROM ers_users WHERE ers_username = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                User user = new User();
                user.setId(result.getInt("ers_user_id"));
                user.setUsername(result.getString("ers_username"));
                user.setPassword(result.getString("ers_password"));
                user.setFirstName(result.getString("user_first_name"));
                user.setLastName(result.getString("user_last_name"));
                user.setEmail(result.getString("user_email"));
                user.setUserRoleId(result.getInt("user_role_id"));

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
