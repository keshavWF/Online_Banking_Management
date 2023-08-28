package com.example.demo.serviceTest;

import com.banking.app.model.Account;
import com.banking.app.model.UserCredential;
import com.banking.app.repository.AccountRepository;
import com.banking.app.service.AccountService;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IUserCredentialService userCredentialService;


    @Test
    public void testSaveAccount() {
        Account account = new Account(/* set necessary fields */);
        String userName = "testUser";
        UserCredential mockUserCredential = new UserCredential(); // Create a mock UserCredential instance
        when(userCredentialService.getUserCredentialsByUserName(userName)).thenReturn(mockUserCredential);

        when(accountRepository.save(account)).thenReturn(account);

        accountService.saveAccount(account, userName);

        verify(accountRepository).save(account);
    }

    @Test
    public void testGetDetailsByAccountNumber() {
        int accountNumber = 123;
        Account account = new Account(/* set necessary fields */);

        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));

        Account result = accountService.getDetailsByAccountNumber(accountNumber);

        assertEquals(account, result);
    }

    @Test
    public void testGetUserNameAPI() {
        int accountNumber = 123;
        Account account = new Account(/* set necessary fields */);

        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account));

        String result = accountService.getUserNameAPI(accountNumber);

        assertEquals(account.getUserName(), result);
    }

    @Test
    public void testGetUserNameAPI_NoAccount() {
        int accountNumber = 123;

        when(accountRepository.findById(accountNumber)).thenReturn(Optional.empty());

        String result = accountService.getUserNameAPI(accountNumber);

        assertEquals("noAccount", result);
    }

    @Test
    public void testUpdateDetailsByAccountNumber() {
        Account account = new Account(/* set necessary fields */);
        Account accountData = new Account(/* set necessary fields */);

        when(accountRepository.findById(account.getAccountNumber())).thenReturn(Optional.of(accountData));
        when(accountRepository.save(accountData)).thenReturn(accountData);

        Account result = accountService.updateDetailsByAccountNumber(account);

        assertNotNull(result);
        assertEquals(account.getAccountBalance(), result.getAccountBalance());
        assertEquals(account.getAccountType(), result.getAccountType());

        verify(accountRepository).save(accountData);
    }

    @Test
    public void testUpdateDetailsByAccountNumber_AccountNotFound() {
        Account account = new Account(/* set necessary fields */);

        when(accountRepository.findById(account.getAccountNumber())).thenReturn(Optional.empty());

        Account result = accountService.updateDetailsByAccountNumber(account);

        assertNull(result);

        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void testGetAccountsByUserName() {
        String userName = "testUser";

        List<Account> mockAccountList = new ArrayList<>(); // Create a mock List of Account instances
        when(accountRepository.findByUserCredential_UserName(userName)).thenReturn(mockAccountList);

        List<Account> result = accountService.getAccountsByUserName(userName);

        assertNotNull(result);
    }
}
