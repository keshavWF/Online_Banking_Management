package com.example.demo.configTest;

import com.banking.app.config.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest
@Import(SecurityConfiguration.class)
public class SecurityConfigurationTest {

    @Autowired
    private HttpSecurity httpSecurity;

    @Test
    @WithMockUser
    void testSecurityConfiguration() throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/user/**", "/bank/**", "/otp/**", "/userDetails/**", "/**").permitAll()
                                .anyRequest().authenticated()
                )
                .and()
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .and()
                .authenticationProvider(/* yourAuthenticationProviderHere */);

        // You can further assert on other parts of your configuration
    }

    // You can add more tests for different scenarios and cases
}
