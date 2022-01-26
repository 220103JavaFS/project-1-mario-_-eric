package com.revature.service;

import com.revature.models.User;
import com.revature.models.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService testInstance;

    //private UserServiceTest userService = new UserServiceTest();

    private User testUser = new User();

    private UserDTO userDTO = new UserDTO();

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp(){
        testUser.setUsername("flodev");
        testUser.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
        //MockitoAnnotations.openMocks(this);
        testInstance = new LoginService();
        //Mockito.when(userService.getUserByUsername("flodev")).thenReturn(testUser);
    }

//    @BeforeEach
//    public void setUp(){
//        userDTO.username = "flodev";
//        userDTO.password = "5f4dcc3b5aa765d61d8327deb882cf99";
//        MockitoAnnotations.openMocks(this);
//        testInstance = new LoginService(userService);
////        Mockito.when(userService.getUserByUsername("flodev")).thenReturn();
//    }

    @Test
    //@Order(2)
    public void testLoginSuccess(){
        assertTrue(testInstance.validateAccount(testUser));
    }

//    @Test
//    @Order(2)
//    public void testLoginSuccess(){
//        assertTrue(testInstance.validateAccountString("flodev", "5f4dcc3b5aa765d61d8327deb882cf99"));
//    }

    @Test
    //@Order(3)
    public void testLoginFailUsername(){
        testUser.setUsername("saldkjfa");
        assertFalse(testInstance.validateAccount(testUser));
    }

    @Test
    //@Order(4)
    public void testLoginFailPassword(){
        testUser.setPassword("a8wa9ja9s");
        assertFalse(testInstance.validateAccount(testUser));
    }

    @Test
    //@Order(5)
    public void testLoginFailBoth(){
        testUser.setUsername("user");
        testUser.setPassword("a;lskdjf;al");
        assertFalse(testInstance.validateAccount(testUser));
    }
}