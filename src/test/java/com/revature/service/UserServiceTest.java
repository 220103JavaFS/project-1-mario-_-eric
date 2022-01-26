package com.revature.service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService serviceInstance;

    @Mock
    private UserDAO mockedDAO;

    private User testUser = new User();

    @BeforeEach
    void setUp(){
        testUser.setUsername("marioj");
        MockitoAnnotations.openMocks(this);
        serviceInstance = new UserService(mockedDAO);
        Mockito.when(mockedDAO.getUserByUsername("marioj")).thenReturn(testUser);
    }

    @Test
    void getUserByUsernameSuccess() {
        assertNotNull(serviceInstance.getUserByUsername("marioj"));
    }

    @Test
    void getUserByUsernameFail() {
        assertNull(serviceInstance.getUserByUsername("thiswillbenull"));
    }

    @Test
    void NotEqualUsername(){
        User not_equal = new User(
                888,
                "Testing123",
                "apass",
                "Name",
                "Last_Name",
                "sum@email.com",
                1
        );

        assertNotEquals(testUser, not_equal);
    }
}