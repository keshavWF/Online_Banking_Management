package com.banking.app.controller;

import com.banking.app.model.Account;
import com.banking.app.model.Transaction;
import com.banking.app.service.AdminService;
import com.banking.app.service.Interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    private final IAdminService adminService;


    @Autowired
    public AdminController(IAdminService adminService)
    {
        this.adminService = adminService;
    }
    @GetMapping("/showTransactions")
    public List<Transaction> getTransactionList()
    {
        return adminService.fetchTransactionList();
    }

    @GetMapping("showAccounts")
    public List<Account> getAccountList()
    {
        return adminService.fetchAccountList();
    }
}