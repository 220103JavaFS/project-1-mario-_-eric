package com.revature.service;

import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService serviceInstance;
    @Mock
    private UserService mockedService;

    private User testUser = new User();

    @BeforeEach
    public void setUp(){
        testUser.setUsername("flodev");
        testUser.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
        MockitoAnnotations.openMocks(this);
        serviceInstance = new LoginService(mockedService);
        Mockito.when(serviceInstance.validateAccount(testUser.getUsername(),
                testUser.getPassword())).thenReturn(testUser);
    }

    @Test
    void testLoginSuccess(){
        assertNotNull(serviceInstance.validateAccount(testUser.getUsername(), testUser.getPassword()));
    }

    @Test
    void testLoginFailUsername(){
        assertNull(serviceInstance.validateAccount("BAD-USERNAME", testUser.getPassword()));
    }

    @Test
    void testLoginFailPassword(){
        assertNull(serviceInstance.validateAccount(testUser.getUsername(), "PASSWORD"));
    }

    @Test
    void testLoginFailBoth(){
        assertNull(serviceInstance.validateAccount("BAD-USERNAME", "PASSWORD"));
    }
}