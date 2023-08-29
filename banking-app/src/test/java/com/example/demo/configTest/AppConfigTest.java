package com.example.demo.configTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.banking.app.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.banking.app.repository.CredentialRepository;

class AppConfigTest {

    @Test
    void testUserDetailsService() {
        CredentialRepository userRepository = mock(CredentialRepository.class);
        AppConfig appConfig = new AppConfig(userRepository);

        UserDetailsService userDetailsService = appConfig.userDetailsService();
        assertNotNull(userDetailsService);

        // Test with a sample user name
        when(userRepository.findById("testUser")).thenReturn(/* mock user credential */);
        assertNotNull(userDetailsService.loadUserByUsername("testUser"));

        // Test with a non-existing user name
        when(userRepository.findById("nonExistingUser")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("nonExistingUser"));
    }

    @Test
    void testAuthenticationProvider() {
        CredentialRepository userRepository = mock(CredentialRepository.class);
        AppConfig appConfig = new AppConfig(userRepository);

        AuthenticationProvider authProvider = appConfig.authenticationProvider();
        assertNotNull(authProvider);
        assertTrue(authProvider instanceof DaoAuthenticationProvider);
    }

    @Test
    void testPasswordEncoder() {
        AppConfig appConfig = new AppConfig(/* mock CredentialRepository */);

        PasswordEncoder passwordEncoder = appConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }

    // You can add more tests for other methods...
}
