package com.revature.service;

import com.revature.models.User;
import org.jetbrains.annotations.NotNull;

public class LoginService {

    private UserService userService = new UserService();

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

}
