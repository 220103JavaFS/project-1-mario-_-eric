package com.revature.service;

import com.revature.repos.UserDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    public UserService testUser;

    public LoginServiceTest loginServiceTest;

    @Mock

    private UserDAO userDAO;

    @Test
    void getUserByUsername() {

        assertNotNull(userDAO.getUserByUsername("flodev"));
    }
}