package com.example.demo.serviceTest;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.banking.app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.app.model.User;
import com.banking.app.repository.UserRepository;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Create a mock User
        User mockUser = new User();

        // Call the method to be tested
        userService.saveUser(mockUser);

        // Verify that the save method was called on the repository
        verify(userRepository).save(mockUser);
    }

    @Test
    void testGetUserDetailsByUserName() {
        String userName = "testUser";
        when(userRepository.findById(userName)).thenReturn(Optional.of(new User()));

        // Call the method to be tested
        User result = userService.getUserDetailsByUserName(userName);

        // Perform assertions on the result
        assertNotNull(result);
    }

    @Test
    void testUpdateUserDetails() {
        // Create a mock User
        User mockUser = new User();
        when(userRepository.findById(anyString())).thenReturn(Optional.of(mockUser));

        // Call the method to be tested
        User result = userService.updateUserDetails(mockUser);

        // Perform assertions on the result
        assertNotNull(result);
    }

    @Test
    void testGetUserNameByEmail() {
        String email = "test@example.com";
        when(userRepository.findUserNameByEmail(email)).thenReturn("testUser");

        // Call the method to be tested
        String result = userService.getUserNameByEmail(email);

        // Perform assertions on the result
        assertEquals("testUser", result);
    }

    // Add more tests for other methods...

    // Remember to add tests for scenarios where data might be missing or null.
}
