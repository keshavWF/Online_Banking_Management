package com.banking.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.model.Account;
import com.banking.app.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/add")
	public String add(@RequestBody Account account) {
		return accountService.saveAccount(account).toString();
//		return "new account added";
	}
}
