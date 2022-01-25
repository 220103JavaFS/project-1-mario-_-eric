package com.revature.service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();


    public User getUserById(int id){
        if (id > 0) {
            return userDAO.get(id);
        }
        return null;
    }

    public User getUserByUsername(String username){
        if (!username.isEmpty()){
            return userDAO.getUserByUsername(username);
        }
        return null;
    }


}
