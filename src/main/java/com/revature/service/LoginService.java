package com.revature.service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import org.jetbrains.annotations.NotNull;

public class LoginService {
    // brought in for testing
//    private UserDAO userDAO;

    public LoginService(){
        this.userService = new UserService();
    }
    // constructor for testing
    public LoginService(UserService userService){

        this.userService = userService;
    }

    private UserService userService;

    public boolean validateAccount(@NotNull User a) {
        // Checks fields
        if (a.getUsername().isEmpty() || a.getPassword().isEmpty()) {
            return false;
        }
        // If the user exists
        User db_user = userService.getUserByUsername(a.getUsername());
        if (db_user == null){
            return false;
        }
        // Checks if passwords match
        if (!db_user.getPassword().equals(a.getPassword())){
            return false;
        }
        return true;
    }

//    public boolean validateAccountString(@NotNull String username, String password) {
//        // Checks fields
//        if (username.isEmpty() || password.isEmpty()) {
//            return false;
//        }
//        // If the user exists
//        User db_user = userService.getUserByUsername(username);
//        if (db_user == null){
//            return false;
//        }
//        // Checks if passwords match
//        if (!db_user.getPassword().equals(password)){
//            return false;
//        }
//        return true;
//    }

}
