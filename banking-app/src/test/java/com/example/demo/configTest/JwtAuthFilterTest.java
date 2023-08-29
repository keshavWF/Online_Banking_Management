package com.example.demo.configTest;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.banking.app.config.JwtAuthFilter;
import com.banking.app.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

class JwtAuthFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer mockJwt");
        when(jwtService.extractUserName("mockJwt")).thenReturn("testUser");

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtService.isTokenValid("mockJwt", userDetails)).thenReturn(true);

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        when(userDetails.getAuthorities()).thenReturn(
                (Collection<SimpleGrantedAuthority>) (Collection<? extends org.springframework.security.core.GrantedAuthority>)
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        when(authToken.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authToken);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Verify that SecurityContextHolder was updated
        assertEquals(authToken, SecurityContextHolder.getContext().getAuthentication());
        // Verify that filterChain.doFilter was called
        verify(filterChain).doFilter(request, response);
    }

    // Add more tests for different scenarios...

    // Remember that Spring Security is complex, and these tests are simplified examples.
    // Actual usage may require additional integration or end-to-end testing.
}
