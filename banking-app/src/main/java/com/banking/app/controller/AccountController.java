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
		accountService.saveAccount(account);
	}

	@GetMapping("/fetchAccount/{accountNumber}")
	public Account fetchDetails(@PathVariable int accountNumber){
		final Account fetchedAccount = accountService.getDetailsByAccountNumber(accountNumber);
		return fetchedAccount;
	}

	@PutMapping("/updateAccount")
	public void updateDetails(@RequestBody Account account){
		accountService.updateDetailsByAccountNumber(account);
	}
}
