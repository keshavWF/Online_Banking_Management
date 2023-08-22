package com.banking.app.service;

import com.banking.app.model.Account;
import com.banking.app.model.Transaction;
import com.banking.app.repository.AccountRepository;
import com.banking.app.repository.TransactionRepository;
import com.banking.app.service.Interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService
{
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public List<Transaction> fetchTransactionList()
    {
        return transactionRepository.findAll();
    }

    @Override
    public List<Account> fetchAccountList()
    {
        return accountRepository.findAll();
    }
}