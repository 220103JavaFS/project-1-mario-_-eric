package com.revature.repos;

import com.revature.models.User;

public interface LoginDAO {

    public User login(String username);
}
