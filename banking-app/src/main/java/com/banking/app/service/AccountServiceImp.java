package com.banking.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.model.Account;
import com.banking.app.repository.AccountRepository;

@Service
public class AccountServiceImp implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public Account saveAccount(Account account) {
//		return new Account("");
		return accountRepository.save(account);
	}
}
