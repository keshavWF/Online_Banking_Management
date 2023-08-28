package com.example.demo.serviceTest;

import com.banking.app.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExtractUserName() {
        // Given
        String token = "validToken";
        when(jwtService.extractClaim(token, Claims::getSubject)).thenReturn("username");

        // When
        String userName = jwtService.extractUserName(token);

        // Then
        assertEquals("username", userName);
    }

    @Test
    void testGenerateToken() {
        // Given
        when(userDetails.getUsername()).thenReturn("username");
        when(jwtService.getSignInKey()).thenReturn(Keys.secretKeyFor(SignatureAlgorithm.HS256));

        // When
        String token = jwtService.generateToken(userDetails);

        // Then
        assertNotNull(token);
        // You might want to verify the token content here if needed
    }

    @Test
    void testIsTokenValid_ValidToken() {
        // Given
        String token = "validToken";
        when(userDetails.getUsername()).thenReturn("username");
        when(jwtService.extractUserName(token)).thenReturn("username");
        when(jwtService.isTokenExpired(token)).thenReturn(false);

        // When
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        // Then
        assertTrue(isValid);
    }

    @Test
    void testIsTokenValid_ExpiredToken() {
        // Given
        String token = "expiredToken";
        when(userDetails.getUsername()).thenReturn("username");
        when(jwtService.extractUserName(token)).thenReturn("username");
        when(jwtService.isTokenExpired(token)).thenReturn(true);

        // When
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        // Then
        assertFalse(isValid);
    }

    // Add more test cases for other methods as needed

}
