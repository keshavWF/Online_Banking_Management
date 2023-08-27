package com.banking.app.service;

import com.banking.app.model.Transaction;
import com.banking.app.model.Account; // Import the Account class
import com.banking.app.repository.AccountRepository;
import com.banking.app.repository.TransactionRepository;
import com.banking.app.service.Interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String settleTransaction(Transaction transactionDetails) {
        transactionRepository.save(transactionDetails);

        int accountNumberPayer = transactionDetails.getPayerAccountNumber();
        Account accountPayer = accountRepository.findByAccountNumber(accountNumberPayer);
        double amount = transactionDetails.getAmount();

        int accountNumberPayee = transactionDetails.getPayeeAccountNumber();
        Account accountPayee = accountRepository.findByAccountNumber(accountNumberPayee);

        if (accountPayee != null && accountPayer != null && (accountPayer.getAccountBalance() - amount) >= 0) {
            accountPayer.setAccountBalance(accountPayer.getAccountBalance() - amount);
            accountPayee.setAccountBalance(accountPayee.getAccountBalance() + amount);

            accountRepository.save(accountPayee);
            accountRepository.save(accountPayer);
            return "Transaction successful" ;
        }
        else if(accountPayee == null)
        {
            accountPayer.setAccountBalance(accountPayer.getAccountBalance() - amount);
            accountRepository.save(accountPayer);
            return "Failed";
        }
        return "Failed";
    }

    @Override
    public List<Transaction> getTransactions(int accountNumber, int numberOfTransactions){

        List<Transaction> fromAccountList = transactionRepository.findTransactionsFromAccountNumber(accountNumber);
        List<Transaction> toAccountList = transactionRepository.findTransactionsToAccountNumber(accountNumber);
        fromAccountList.addAll(toAccountList);

        sortTransactions(fromAccountList);

        List<Transaction> res = new ArrayList<>();

        for(int i=fromAccountList.size()-1; i>=0 && numberOfTransactions>0 ; i--, numberOfTransactions--){
            res.add(fromAccountList.get(i));
        }

        return res;
    }



    @Override
    public List<Transaction> getAllTransactionsByUserName(String userName){
        return transactionRepository.findTransactionsForUsername(userName);
    }

    private void sortTransactions(List<Transaction> transactions){
        Comparator<Transaction> timeComparator = Comparator.comparing(Transaction::getDate);
        Collections.sort(transactions, timeComparator);
    }
}
