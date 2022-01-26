package com.revature.service;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    public UserService testInstance;
    //@Mock
    private User testUser = new User();

    @BeforeEach
    void setUp(){
        //testUser = mockedDAO.getUserByUsername("marioj");
        //testUser.setUsername("marioj");
        //MockitoAnnotations.openMocks(this);
        testInstance = new UserService();
        //Mockito.when(mockedDAO.getUserByUsername("marioj")).thenReturn(testUser);
    }

    @Test
    void getUserByUsername() {

        assertNotNull(testInstance.getUserByUsername("marioj"));
//        System.out.println(testUser);
//        User test_user = mockedDAO.getUserByUsername("marioj");
//        System.out.println(test_user);
//        assertEquals(testUser, test_user);
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