package com.banking.app.controller;


import com.banking.app.model.Transaction;
import com.banking.app.service.Interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/makeTransaction")
    public String makeTransaction(@RequestBody Transaction transactionDetails) {
        return transactionService.settleTransaction(transactionDetails);
    }

    @GetMapping("/fetchTransactions/{accountNumber}/{numberOfTransactions}")
    public List<Transaction> fetchAllTransactionsForAccount(@PathVariable int accountNumber, @PathVariable int numberOfTransactions){
        return transactionService.getTransactions(accountNumber,numberOfTransactions);
    }

    @GetMapping("/getTransactions/{userName}")
    public List<Transaction> fetchAllTransactionsByUserName(@PathVariable String userName){
        return transactionService.getAllTransactionsByUserName(userName);
    }

    @GetMapping("/currentDateTime")
    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        return now.format(formatter);
    }
}