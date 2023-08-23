package com.banking.app.service.Interfaces;

import com.banking.app.model.Transaction;
import java.util.List;

public interface ITransactionService {

    public String settleTransaction(Transaction transactionDetails);

    public List<Transaction> getTransactions(int accountNumber, int numberOfTransactions);

    public List<Transaction> getAllTransactionsByUserName(String userName);
}
