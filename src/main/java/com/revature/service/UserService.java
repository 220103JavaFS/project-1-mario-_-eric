package com.revature.service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import org.jetbrains.annotations.NotNull;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO dao) {
        this.userDAO = dao;
    }

    public UserService(){
        this.userDAO = new UserDAOImpl();
    }

    public User getUserById(int id){
        if (id > 0) {
            return userDAO.get(id);
        }
        return null;
    }

    public User getUserByUsername(@NotNull String username){
        if (!username.isEmpty()){
            return userDAO.getUserByUsername(username);
        }
        return null;
    }


}
