package com.example.demo.repositoryTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.app.repository.UserRepository;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRepository userRepositoryUnderTest;

    @Test
    void testFindUserNameByEmail() {
        String email = "test@example.com";
        String expectedUserName = "testUser";
        when(userRepository.findUserNameByEmail(email)).thenReturn(expectedUserName);

        // Call the method to be tested
        String result = userRepositoryUnderTest.findUserNameByEmail(email);

        // Perform assertions on the result
        assertEquals(expectedUserName, result);
    }

    // Add more tests for other methods...

    // Remember to add tests for various scenarios and edge cases.
}
