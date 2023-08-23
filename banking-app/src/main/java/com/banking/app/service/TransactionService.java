package com.banking.app.service;

import com.banking.app.model.Transaction;
import com.banking.app.model.Account; // Import the Account class
import com.banking.app.repository.AccountRepository;
import com.banking.app.repository.TransactionRepository;
import com.banking.app.service.Interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String settleTransaction(Transaction transactionDetails) {
        transactionRepository.save(transactionDetails);
        String toReturn = "";
        int accountNumberPayer = transactionDetails.getPayerAccountNumber();

        Account accountPayer = accountRepository.findByAccountNumber(accountNumberPayer);
        double amount = transactionDetails.getAmount();
        toReturn+= "amount: " + Double.toString(amount);
        int accountNumberPayee = transactionDetails.getPayeeAccountNumber();
        Account accountPayee = accountRepository.findByAccountNumber(accountNumberPayee);

        if (accountPayee != null && accountPayer != null) {
            accountPayer.setAccountBalance(accountPayer.getAccountBalance() - amount);
            accountPayee.setAccountBalance(accountPayee.getAccountBalance() + amount);

            accountRepository.save(accountPayee);
            accountRepository.save(accountPayer);
            return "Transaction successful" ;//+ toReturn;
        }
        return "Transaction failed";
    }
}
