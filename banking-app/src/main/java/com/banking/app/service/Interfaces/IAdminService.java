package com.banking.app.service.Interfaces;

import com.banking.app.model.Account;
import com.banking.app.model.Transaction;
import java.util.List;

public interface IAdminService
{
    public List<Transaction> fetchTransactionList();
    public List<Account> fetchAccountList();
}