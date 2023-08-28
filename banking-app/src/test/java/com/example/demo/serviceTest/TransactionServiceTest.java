package com.example.demo.serviceTest;

import com.banking.app.model.Account;
import com.banking.app.model.Transaction;
import com.banking.app.model.UserCredential;
import com.banking.app.repository.AccountRepository;
import com.banking.app.repository.TransactionRepository;
import com.banking.app.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void testSettleTransaction_SuccessfulTransaction() {
        // Create mock Account instances

//        UserCredential payerCredentials = new UserCredential("Payer1", "101", "False", "");
//        UserCredential payeeCredentials = new UserCredential();
//        Account accountPayer = mock(Account.class);
        Account accountPayer = new Account(/* set necessary fields */);
        Account accountPayee = new Account(/* set necessary fields */);

        // Set up the mock behavior
        when(accountRepository.findByAccountNumber(anyInt()))
                .thenReturn(accountPayer)
                .thenReturn(accountPayee);

        // Call the method to be tested
        Transaction transactionDetails = new Transaction(/* set necessary fields */);
        String result = transactionService.settleTransaction(transactionDetails);

        // Verify changes to account balances and repository saves
        int accountBalancePayer = 100;
        int accountBalancePayee = 101;
        verify(accountPayer).setAccountBalance(accountBalancePayer);
        verify(accountPayee).setAccountBalance(accountBalancePayee);
        verify(accountRepository, times(2)).save(any(Account.class));

        // Verify the returned result
        assertEquals("Transaction successful", result);
    }


    @Test
    public void testSettleTransaction_UnsuccessfulTransaction() {
        // Create mock Account instances
        Account accountPayer = mock(Account.class);

        // Set up the mock behavior
        when(accountRepository.findByAccountNumber(anyInt())).thenReturn(accountPayer);

        // Call the method to be tested with an amount that exceeds the payer's balance
        Transaction transactionDetails = new Transaction(/* set necessary fields */);
        String result = transactionService.settleTransaction(transactionDetails);

        // Verify that the account balance is not modified
        verify(accountPayer, never()).setAccountBalance(anyDouble());

        // Verify that the repository save is not called
        verify(accountRepository, never()).save(any(Account.class));

        // Verify the returned result
        assertEquals("Failed", result);
    }



    @Test
    public void testGetTransactions() {
        // Set up mock Transaction instances
        List<Transaction> mockTransactions = new ArrayList<>();
        // Add mock transactions to the list

        // Set up mock repository behavior
        when(transactionRepository.findTransactionsFromAccountNumber(anyInt())).thenReturn(mockTransactions);
        when(transactionRepository.findTransactionsToAccountNumber(anyInt())).thenReturn(Collections.emptyList());

        // Call the method to be tested
        int accountNumber = 100101;
        int numberOfTransactions = 2;
        List<Transaction> result = transactionService.getTransactions(accountNumber, numberOfTransactions);

        // Verify the returned result
        assertEquals(mockTransactions, result);
    }

}
