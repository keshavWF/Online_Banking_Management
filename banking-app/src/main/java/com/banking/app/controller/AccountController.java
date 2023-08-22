package com.banking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banking.app.model.Account;
import com.banking.app.service.Interfaces.IAccountService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/addAccount/{userName}")
	public void add(@RequestBody Account account, @PathVariable String userName) {
		accountService.saveAccount(account, userName);
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

	@GetMapping("/getAccount/{userName}")
	public List<Account> getAccountDetailsByUserName(@PathVariable String userName){
		return accountService.getAccountsByUserName(userName);
	}
}
