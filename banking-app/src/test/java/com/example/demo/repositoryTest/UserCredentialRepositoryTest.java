package com.example.demo.repositoryTest;
import com.banking.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.query.Param;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCredentialRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindUserNameByEmail() {
        String email = "test@example.com";
        String expectedUserName = "testUser";

        // Mock the behavior of the repository method
        when(userRepository.findUserNameByEmail(email)).thenReturn(expectedUserName);

        // Call the method to be tested
        String result = userRepository.findUserNameByEmail(email);

        // Verify that the method was called with the correct parameter
        verify(userRepository).findUserNameByEmail(email);

        // Verify the returned result
        assertEquals(expectedUserName, result);
    }
}
