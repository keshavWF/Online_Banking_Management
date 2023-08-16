package com.banking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banking.app.model.Account;
import com.banking.app.service.Interfaces.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/addAccount")
	public void add(@RequestBody Account account) {
		System.out.println("Account added successfully!!");
		accountService.saveAccount(account);
		System.out.println("Account added successfully!!");
	}

	@GetMapping("/fetchAccount/{accountNumber}")
	public void fetchDetails(@PathVariable int accountNumber){
		final Account fetchedAccount = accountService.getDetailsByAccountNumber(accountNumber);
		if(fetchedAccount != null) {
			System.out.println("Account Number: " + fetchedAccount.getAccountNumber());
			System.out.println("Account Balance: " + fetchedAccount.getAccountBalance());
			System.out.println("Account Type: " + fetchedAccount.getAccountType());
			System.out.println("User ID: " + fetchedAccount.getUserID());
		}
		else
			System.out.println("Cannot fetch account details!");
	}

	@PutMapping("/updateAccount")
	public void updateDetails(@RequestBody Account account){
		accountService.updateDetailsByAccountNumber(account);
		System.out.println("Account updated successfully!!");
	}
}
