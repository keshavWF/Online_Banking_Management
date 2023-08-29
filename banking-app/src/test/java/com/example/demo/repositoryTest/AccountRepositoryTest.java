package com.example.demo.repositoryTest;

import com.banking.app.model.Account;
import com.banking.app.model.UserCredential;
import com.banking.app.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class AccountRepositoryTest {

    @Mock
    private AccountRepository accountRepositoryMock;

    @Test
    public void testFindByAccountNumber_Success() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Create a mock Account instance
        Account mockAccount = new Account();
        mockAccount.setAccountNumber(12345);

        // Set up the mock behavior
        when(accountRepositoryMock.findByAccountNumber(12345)).thenReturn(mockAccount);

        // Call the method to be tested
        Account result = accountRepositoryMock.findByAccountNumber(12345);

        // Verify the result
        assertEquals(mockAccount, result);

        // Verify that the mock was called
        verify(accountRepositoryMock).findByAccountNumber(12345);
    }

    @Test
    public void testFindByAccountNumber_NotFound() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Set up the mock behavior to return null (not found)
        when(accountRepositoryMock.findByAccountNumber(12345)).thenReturn(null);

        // Call the method to be tested
        Account result = accountRepositoryMock.findByAccountNumber(12345);

        // Verify the result is null
        assertNull(result);

        // Verify that the mock was called
        verify(accountRepositoryMock).findByAccountNumber(12345);
    }



    @Test
    public void testFindByUserCredential_UserName_Success() {
        // Given
        UserCredential userCredential = new UserCredential(/* set necessary fields */);
        Account account1 = new Account();
        Account account2 = new Account();
        accountRepositoryMock.save(account1);
        accountRepositoryMock.save(account2);

        // When
        List<Account> accounts = accountRepositoryMock.findByUserCredential_UserName(userCredential.getUserName());

        // Then
        assertEquals(2, accounts.size());
        // Add additional assertions as needed
    }

    @Test
    public void testFindByUserCredential_UserName_Failure() {
        // Given
        String nonExistingUserName = "non_existing_user";

        // When
        List<Account> accounts = accountRepositoryMock.findByUserCredential_UserName(nonExistingUserName);

        // Then
        assertEquals(0, accounts.size());
    }
}
