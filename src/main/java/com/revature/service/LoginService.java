package com.revature.service;

import com.revature.models.User;
import org.jetbrains.annotations.NotNull;

public class LoginService {

    private UserService userService;

    public LoginService(){

        this.userService = new UserService();
    }

    // constructor for testing
    public LoginService(UserService userService){

        this.userService = userService;
    }

    public User validateAccount(@NotNull String username, @NotNull String  password) {
        // Checks fields
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        // If the user exists
        User db_user = userService.getUserByUsername(username);
        if (db_user == null){
            return null;
        }
        // Checks if passwords match
        if (!db_user.getPassword().equals(password)){
            return null;
        }
        return db_user;
    }

}
