package com.example.demo.serviceTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.banking.app.service.PayeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.app.model.Payee;
import com.banking.app.model.UserCredential;
import com.banking.app.repository.PayeeRepository;
import com.banking.app.service.Interfaces.IUserCredentialService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PayeeServiceTest {

    @Mock
    private PayeeRepository payeeRepository;

    @Mock
    private IUserCredentialService userCredentialService;
    @Mock
    private UserCredential userCredential;

    @InjectMocks
    private PayeeService payeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePayee() {
        // Create a mock Payee and mock user credentials
        Payee mockPayee = new Payee();
        when(userCredentialService.getUserCredentialsByUserName(anyString())).thenReturn(userCredential);

        // Call the method to be tested
        payeeService.savePayee(mockPayee, "testUser");

        // Verify that the save method was called on the repository
        verify(payeeRepository).save(mockPayee);
    }

    @Test
    void testGetPayeeDetailsByUserID() {
        int payeeID = 123;
        when(payeeRepository.findById(payeeID)).thenReturn(Optional.of(new Payee()));

        // Call the method to be tested
        Payee result = payeeService.getPayeeDetailsByUserID(payeeID);

        // Perform assertions on the result
        assertNotNull(result);
    }

    @Test
    void testUpdatePayeeDetails() {
        // Create a mock Payee
        Payee mockPayee = new Payee();
        when(payeeRepository.findById(anyInt())).thenReturn(Optional.of(mockPayee));

        // Call the method to be tested
        Payee result = payeeService.updatePayeeDetails(mockPayee);

        // Perform assertions on the result
        assertNotNull(result);
    }

    // Add more tests for other methods...

    // Remember to add tests for scenarios where data might be missing or null.
}
