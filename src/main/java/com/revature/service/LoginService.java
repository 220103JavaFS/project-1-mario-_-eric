package com.revature.service;

import com.revature.models.User;
import com.revature.repos.LoginDAO;
import com.revature.repos.LoginDAOImpl;
import com.revature.utils.EncryptionUtil;

public class LoginService {

    LoginDAO loginDAO = new LoginDAOImpl();

    public User getLoginUsername(String username) {
        if (!username.isEmpty()) {
            return loginDAO.login(username);
        }
        return null;
    }

    public ResponseType login(User u) {

        User user = getLoginUsername(u.getUsername());

        u.setPassword(EncryptionUtil.stringToMD5(u.getPassword()));

        if (!user.getPassword().equals(u.getPassword())){
            return ResponseType.INVALID_PASSWORD;
        }

        return ResponseType.SUCCESS;

    }
}
