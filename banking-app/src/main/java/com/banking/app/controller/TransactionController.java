package com.banking.app.controller;


import com.banking.app.model.Transaction;
import com.banking.app.service.Interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}