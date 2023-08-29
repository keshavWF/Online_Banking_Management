package com.example.demo.serviceTest;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.banking.app.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.app.model.Account;
import com.banking.app.model.Transaction;
import com.banking.app.repository.AccountRepository;
import com.banking.app.repository.TransactionRepository;
import com.banking.app.service.Interfaces.IAdminService;

import java.util.ArrayList;
import java.util.List;

class AdminServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchTransactionList() {
        // Create mock transactions
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction());
        mockTransactions.add(new Transaction());

        // Mock the behavior of transactionRepository.findAll()
        when(transactionRepository.findAll()).thenReturn(mockTransactions);

        // Call the method to be tested
        List<Transaction> result = adminService.fetchTransactionList();

        // Verify the result
        assertEquals(mockTransactions.size(), result.size());
    }

    @Test
    void testFetchAccountList() {
        // Create mock accounts
        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new Account());
        mockAccounts.add(new Account());

        // Mock the behavior of accountRepository.findAll()
        when(accountRepository.findAll()).thenReturn(mockAccounts);

        // Call the method to be tested
        List<Account> result = adminService.fetchAccountList();

        // Verify the result
        assertEquals(mockAccounts.size(), result.size());
    }

    // Add more tests for edge cases and error scenarios...
}
