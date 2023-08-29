package com.example.demo.serviceTest;

import com.banking.app.model.*;
import com.banking.app.repository.CredentialRepository;
import com.banking.app.service.Interfaces.IUserCredentialService;
import com.banking.app.service.JwtService;
import com.banking.app.service.UserCredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserCredentialServiceTest {

    @InjectMocks
    private UserCredentialService userCredentialService;

    @Mock
    private CredentialRepository credentialRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    public void testSaveUserCredentials() {
        /// ??????????????? JWT TOKEN MOCK ???????????????????
        RegisterRequest registerRequest = new RegisterRequest(/* set necessary fields */);
        UserCredential userCredential = new UserCredential(/* set necessary fields */);
        String jwtToken = "generatedJwtToken";

        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(jwtService.generateToken(userCredential)).thenReturn(jwtToken);

        // When
        AuthenticationResponse response = userCredentialService.saveUserCredentials(registerRequest);

        // Then
        verify(credentialRepository).save(any(UserCredential.class));
        assertEquals(jwtToken, response.getToken());
    }



    // Similar tests for other methods in UserCredentialService
}
